package com.hao.demo.ddd.domain.entity;


import com.hao.demo.ddd.dp.DamageType;
import com.hao.demo.ddd.dp.WeaponId;
import com.hao.demo.ddd.dp.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weapon {
    private WeaponId id;
    private String name;
    private WeaponType weaponType; // enum
    private int damage;

    public DamageType getDamageType() {
        return weaponType.getDamageType();
    }

}

