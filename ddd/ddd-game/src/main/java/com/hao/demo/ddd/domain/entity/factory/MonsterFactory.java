package com.hao.demo.ddd.domain.entity.factory;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.dp.Health;
import com.hao.demo.ddd.dp.MonsterClass;

public class MonsterFactory {


    public Monster createMonster(MonsterClass monsterClass, int health) {
        return new Monster(null, monsterClass, new Health(health));
    }
}
