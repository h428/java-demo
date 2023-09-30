package com.hao.demo.ddd.repository;

import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.dp.WeaponId;

public interface WeaponRepository {
    Weapon find(WeaponId weaponId);
}
