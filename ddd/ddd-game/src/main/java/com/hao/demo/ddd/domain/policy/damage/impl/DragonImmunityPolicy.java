package com.hao.demo.ddd.domain.policy.damage.impl;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.DamagePolicy;
import com.hao.demo.ddd.dp.MonsterClass;
import com.hao.demo.ddd.dp.PlayerClass;

// 策略：龙免疫物理和魔法
public class DragonImmunityPolicy implements DamagePolicy {

    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return player.getPlayerClass() != PlayerClass.Dragoon &&
                monster.getMonsterClass() == MonsterClass.Dragon;
    }

    @Override
    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return 0;
    }

}

