package com.hao.demo.ddd;

import static org.assertj.core.api.Assertions.assertThat;

import com.hao.demo.ddd.domain.components.move.MovementSystem;
import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.entity.factory.MonsterFactory;
import com.hao.demo.ddd.domain.entity.factory.PlayerFactory;
import com.hao.demo.ddd.domain.entity.factory.WeaponFactory;
import com.hao.demo.ddd.domain.entity.prototype.WeaponPrototype;
import com.hao.demo.ddd.domain.service.CombatService;
import com.hao.demo.ddd.domain.service.EquipmentService;
import com.hao.demo.ddd.domain.service.impl.CombatServiceImpl;
import com.hao.demo.ddd.domain.service.impl.EquipmentServiceImpl;
import com.hao.demo.ddd.dp.Health;
import com.hao.demo.ddd.dp.MonsterClass;
import com.hao.demo.ddd.dp.PlayerClass;
import com.hao.demo.ddd.repository.WeaponRepository;
import com.hao.demo.ddd.repository.WeaponRepositoryMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 出于方便所有文章中出现的测试都写在一起
public class MainTest {

    PlayerFactory playerFactory = new PlayerFactory();

    WeaponFactory weaponFactory = new WeaponFactory();

    WeaponPrototype swordProto = WeaponPrototype.defaultSword;

    WeaponRepository weaponRepository = new WeaponRepositoryMock();

    MonsterFactory monsterFactory = new MonsterFactory();

    EquipmentService equipmentService = new EquipmentServiceImpl();

    CombatService combatService = new CombatServiceImpl(weaponRepository);


    @Test
    @DisplayName("Dragoon attack dragon doubles damage")
    public void testDragoonSpecial() {
        // Given
        Player dragoon = playerFactory.createPlayer(PlayerClass.Dragoon, "Dart");
        Weapon sword = weaponFactory.createWeaponFromPrototype(swordProto, "Soul Eater", 60);
        ((WeaponRepositoryMock)weaponRepository).cache(sword);
        dragoon.equip(sword, equipmentService);
        Monster dragon = monsterFactory.createMonster(MonsterClass.Dragon, 100);

        // When
        combatService.performAttack(dragoon, dragon);

        // Then
        assertThat(dragon.getHealth()).isEqualTo(Health.Zero);
        assertThat(dragon.isAlive()).isFalse();
    }

    @Test
    @DisplayName("Orc should receive half damage from physical weapons")
    public void testFighterOrc() {
        // Given
        Player fighter = playerFactory.createPlayer(PlayerClass.Fighter, "MyFighter");
        Weapon sword = weaponFactory.createWeaponFromPrototype(swordProto, "My Sword");
        ((WeaponRepositoryMock)weaponRepository).cache(sword);
        fighter.equip(sword, equipmentService);
        Monster orc = monsterFactory.createMonster(MonsterClass.Orc, 100);

        // When
        combatService.performAttack(fighter, orc);
        // Then
        assertThat(orc.getHealth()).isEqualTo(Health.of(100 - 10 / 2));
    }

    MovementSystem movementSystem = new MovementSystem();

    @Test
    @DisplayName("Moving player and monster at the same time")
    public void testMovement() {
        // Given
        Player fighter = playerFactory.createPlayer(PlayerClass.Fighter, "MyFighter");
        fighter.moveTo(2, 5);
        fighter.startMove(1, 0);

        Monster orc = monsterFactory.createMonster(MonsterClass.Orc, 100);
        orc.moveTo(10, 5);
        orc.startMove(-1, 0);

        movementSystem.register(fighter);
        movementSystem.register(orc);

        // When
        movementSystem.update();

        // Then
        assertThat(fighter.getPosition().x()).isEqualTo(2 + 1);
        assertThat(orc.getPosition().x()).isEqualTo(10 - 1);
    }



}
