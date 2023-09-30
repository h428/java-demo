package com.hao.demo.ddd.domain.policy.damage.impl;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.DamagePolicy;
import com.hao.demo.ddd.dp.DamageType;
import com.hao.demo.ddd.dp.MonsterClass;

// 策略：精灵对魔法伤害减半
public class ElfResistancePolicy implements DamagePolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamageType() != DamageType.Physical && monster.getMonsterClass() == MonsterClass.Elf;
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamage() / 2;
    }
}

