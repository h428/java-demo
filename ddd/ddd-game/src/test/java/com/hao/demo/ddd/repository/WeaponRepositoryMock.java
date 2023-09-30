package com.hao.demo.ddd.repository;

import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.dp.WeaponId;
import java.util.HashMap;
import java.util.Map;

public class WeaponRepositoryMock implements WeaponRepository {

    private final Map<WeaponId, Weapon> weapons = new HashMap<>();

    // 用于在 mock 中存储 Weapon
    public void cache(Weapon weapon) {
        weapons.put(weapon.getId(), weapon);
    }

    @Override
    public Weapon find(WeaponId weaponId) {
        return weapons.get(weaponId);
    }

}
