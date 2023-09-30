package com.hao.demo.ddd.domain.entity.prototype;

import com.hao.demo.ddd.dp.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WeaponPrototype {

    private WeaponType weaponType;
    private int damage;

    public static WeaponPrototype defaultSword = new WeaponPrototype(WeaponType.Sword, 10);

}
