package com.hao.demo.ddd.domain.service.impl;

import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.equipment.EquipmentManager;
import com.hao.demo.ddd.domain.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentManager equipmentManager = EquipmentManager.getInstance();

    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return equipmentManager.canEquip(player, weapon);
    }
}
