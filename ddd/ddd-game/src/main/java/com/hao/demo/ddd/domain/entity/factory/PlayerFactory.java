package com.hao.demo.ddd.domain.entity.factory;

import com.hao.demo.ddd.domain.entity.Player;
import com.hao.demo.ddd.dp.PlayerClass;
import com.hao.demo.ddd.dp.WeaponId;

public class PlayerFactory {

    public Player createPlayer(PlayerClass playerClass, String name) {
        return new Player(null, name, playerClass, null);
    }

    public Player createPlayer(PlayerClass playerClass, String name, WeaponId weaponId) {
        return new Player(null, name, playerClass, weaponId);
    }
}
