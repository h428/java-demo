package com.hao.demo.ddd.domain.policy.damage.impl;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.DamagePolicy;

// 策略：兜底策略
public class DefaultDamagePolicy implements DamagePolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return true;
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamage();
    }
}

