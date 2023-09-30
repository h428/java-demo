package com.hao.demo.ddd.domain.policy.equipment;

import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;

public interface EquipmentPolicy {
    boolean canApply(Player player, Weapon weapon);
    boolean canEquip(Player player, Weapon weapon);
}
