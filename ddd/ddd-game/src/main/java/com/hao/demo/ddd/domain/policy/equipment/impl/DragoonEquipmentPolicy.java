package com.hao.demo.ddd.domain.policy.equipment.impl;


import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.equipment.EquipmentPolicy;
import com.hao.demo.ddd.dp.PlayerClass;

// 策略案例
public class DragoonEquipmentPolicy implements EquipmentPolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon) {
        return player.getPlayerClass() == PlayerClass.Dragoon;
    }

    /**
     * Dragoon 能装备所有武器
     */
    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return true;
    }

}
