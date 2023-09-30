package com.hao.demo.ddd.domain.service;

import com.hao.demo.ddd.domain.entity.Monster;
import com.hao.demo.ddd.domain.entity.Player;

public interface CombatService {
    void performAttack(Player player, Monster monster);
}
