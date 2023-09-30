package com.hao.demo.ddd.domain.policy.damage;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;

public interface DamagePolicy {

    boolean canApply(Player player, Weapon weapon, Monster monster);

    int calculateDamage(Player player, Weapon weapon, Monster monster);

}
