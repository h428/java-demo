package com.hao.demo.ddd.domain.policy.damage.impl;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.DamagePolicy;
import com.hao.demo.ddd.dp.DamageType;

// 策略：物理攻击
public class PhysicalDamagePolicy implements DamagePolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamageType() == DamageType.Physical;
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamage();
    }
}

