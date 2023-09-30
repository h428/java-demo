package com.hao.demo.ddd.domain.service;

import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;

public interface EquipmentService {

    boolean canEquip(Player player, Weapon weapon);

}
