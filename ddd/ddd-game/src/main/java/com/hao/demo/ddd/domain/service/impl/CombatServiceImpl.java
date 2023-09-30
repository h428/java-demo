package com.hao.demo.ddd.domain.service.impl;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.DamageManager;
import com.hao.demo.ddd.domain.service.CombatService;
import com.hao.demo.ddd.repository.WeaponRepository;

public class CombatServiceImpl implements CombatService {

    private final WeaponRepository weaponRepository; // 倒置依赖 Infrastructure-持久层

    private final DamageManager damageManager = DamageManager.getInstance(); // 伤害策略管理器

    public CombatServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void performAttack(Player player, Monster monster) {
        Weapon weapon = weaponRepository.find(player.getWeaponId());
        int damage = damageManager.calculateDamage(player, weapon, monster);
        if (damage > 0) {
            monster.takeDamage(damage); // （Note 1）在领域服务里变更Monster
        }
        // 省略掉Player和Weapon可能受到的影响
    }

}
