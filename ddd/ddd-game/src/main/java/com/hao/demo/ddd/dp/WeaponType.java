package com.hao.demo.ddd.dp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WeaponType {
    Sword(DamageType.Physical),
    FireStaff(DamageType.Fire),
    IceStaff(DamageType.Ice),
    Dagger(DamageType.Physical);

    final DamageType damageType;

}
