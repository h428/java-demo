package com.hao.demo.ddd.domain.policy.equipment;

import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.equipment.impl.DefaultEquipmentPolicy;
import com.hao.demo.ddd.domain.policy.equipment.impl.DragoonEquipmentPolicy;
import com.hao.demo.ddd.domain.policy.equipment.impl.FighterEquipmentPolicy;
import com.hao.demo.ddd.domain.policy.equipment.impl.MageEquipmentPolicy;
import java.util.ArrayList;
import java.util.List;

// 策略优先级管理
public class EquipmentManager {

    private static final EquipmentManager equipmentManager = new EquipmentManager();

    private EquipmentManager() {
    }

    public static EquipmentManager getInstance() {
        return equipmentManager;
    }

    private static final List<EquipmentPolicy> POLICIES = new ArrayList<>();
    static {
        POLICIES.add(new FighterEquipmentPolicy());
        POLICIES.add(new MageEquipmentPolicy());
        POLICIES.add(new DragoonEquipmentPolicy());
        POLICIES.add(new DefaultEquipmentPolicy());
    }

    public boolean canEquip(Player player, Weapon weapon) {
        for (EquipmentPolicy policy : POLICIES) {
            if (!policy.canApply(player, weapon)) {
                continue;
            }
            return policy.canEquip(player, weapon);
        }
        return false;
    }
}

