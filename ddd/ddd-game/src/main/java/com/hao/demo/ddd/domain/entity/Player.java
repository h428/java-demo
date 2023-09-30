package com.hao.demo.ddd.domain.entity;


import com.hao.demo.ddd.domain.components.move.Movable;
import com.hao.demo.ddd.domain.service.EquipmentService;
import com.hao.demo.ddd.dp.PlayerClass;
import com.hao.demo.ddd.dp.PlayerId;
import com.hao.demo.ddd.dp.Transform;
import com.hao.demo.ddd.dp.Vector;
import com.hao.demo.ddd.dp.WeaponId;
import lombok.Getter;

// 具体实现
@Getter
public class Player implements Movable {

    private PlayerId id;
    private String name;
    private PlayerClass playerClass; // enum
    private WeaponId weaponId; //
    private Transform position = Transform.ORIGIN;
    private Vector velocity = Vector.ZERO;

    public Player(PlayerId id, String name, PlayerClass playerClass, WeaponId weaponId) {
        this.id = id;
        this.name = name;
        this.playerClass = playerClass;
        this.weaponId = weaponId;
    }

    @Override
    public void moveTo(long x, long y) {
        this.position = new Transform(x, y);
    }

    @Override
    public void startMove(long velocityX, long velocityY) {
        this.velocity = new Vector(velocityX, velocityY);
    }

    @Override
    public void stopMove() {
        this.velocity = Vector.ZERO;
    }

    @Override
    public boolean isMoving() {
        return this.velocity.x() != 0 || this.velocity.y() != 0;
    }

    public void equip(Weapon weapon, EquipmentService equipmentService) {
        if (equipmentService.canEquip(this, weapon)) {
            this.weaponId = weapon.getId();
        } else {
            throw new IllegalArgumentException("Cannot Equip: " + weapon);
        }
    }

}

