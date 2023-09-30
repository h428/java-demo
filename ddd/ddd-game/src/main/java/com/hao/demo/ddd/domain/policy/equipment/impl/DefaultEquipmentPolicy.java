package com.hao.demo.ddd.domain.policy.equipment.impl;


import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.equipment.EquipmentPolicy;
import com.hao.demo.ddd.dp.PlayerClass;
import com.hao.demo.ddd.dp.WeaponType;

// 策略案例
public class DefaultEquipmentPolicy implements EquipmentPolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon) {
        return player.getPlayerClass() == PlayerClass.Mage;
    }

    /**
     * 默认只能装备 Sword
     */
    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return weapon.getWeaponType() == WeaponType.Sword;
    }

}
