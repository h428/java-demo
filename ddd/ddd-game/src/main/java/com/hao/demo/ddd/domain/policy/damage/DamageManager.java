package com.hao.demo.ddd.domain.policy.damage;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.domain.entity.Weapon;
import com.hao.demo.ddd.domain.policy.damage.impl.DefaultDamagePolicy;
import com.hao.demo.ddd.domain.policy.damage.impl.DragonImmunityPolicy;
import com.hao.demo.ddd.domain.policy.damage.impl.DragoonPolicy;
import com.hao.demo.ddd.domain.policy.damage.impl.ElfResistancePolicy;
import com.hao.demo.ddd.domain.policy.damage.impl.OrcResistancePolicy;
import com.hao.demo.ddd.domain.policy.damage.impl.PhysicalDamagePolicy;
import java.util.ArrayList;
import java.util.List;

// 策略优先级管理
public class DamageManager {

    private static final DamageManager damageManager = new DamageManager();

    private static final List<DamagePolicy> POLICIES = new ArrayList<>();

    static {
        POLICIES.add(new DragoonPolicy());
        POLICIES.add(new DragonImmunityPolicy());
        POLICIES.add(new OrcResistancePolicy());
        POLICIES.add(new ElfResistancePolicy());
        POLICIES.add(new PhysicalDamagePolicy());
        POLICIES.add(new DefaultDamagePolicy());
    }

    public static DamageManager getInstance() {
        return damageManager;
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        for (DamagePolicy policy : POLICIES) {
            if (!policy.canApply(player, weapon, monster)) {
                continue;
            }
            return policy.calculateDamage(player, weapon, monster);
        }
        return 0;
    }
}

