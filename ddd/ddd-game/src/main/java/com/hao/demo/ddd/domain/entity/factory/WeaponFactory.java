package com.hao.demo.ddd.domain.entity.factory;

import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.entity.prototype.WeaponPrototype;

public class WeaponFactory {

    public Weapon createWeaponFromPrototype(WeaponPrototype proto, String newName) {
        return new Weapon(null, newName, proto.getWeaponType(), proto.getDamage());
    }

    public Weapon createWeaponFromPrototype(WeaponPrototype proto, String newName, int damage) {
        return new Weapon(null, newName, proto.getWeaponType(), damage);
    }

}
