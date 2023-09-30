package com.hao.demo.ddd.domain.entity;


import com.hao.demo.ddd.domain.components.move.Movable;
import com.hao.demo.ddd.dp.Health;
import com.hao.demo.ddd.dp.MonsterClass;
import com.hao.demo.ddd.dp.MonsterId;
import com.hao.demo.ddd.dp.Transform;
import com.hao.demo.ddd.dp.Vector;
import lombok.Getter;

@Getter
public class Monster implements Movable {
    private MonsterId id;
    private MonsterClass monsterClass; // enum
    private Health health;
    private Transform position = Transform.ORIGIN;
    private Vector velocity = Vector.ZERO;

    public Monster(MonsterId id, MonsterClass monsterClass, Health health) {
        this.id = id;
        this.monsterClass = monsterClass;
        this.health = health;
    }

    public boolean isAlive() {
        return health.value() > 0;
    }

    @Override
    public void moveTo(long x, long y) {
        this.position = new Transform(x, y);
    }

    @Override
    public void startMove(long velocityX, long velocityY) {
        this.velocity = new Vector(velocityX, velocityY);
    }

    @Override
    public void stopMove() {
        this.velocity = Vector.ZERO;
    }

    @Override
    public boolean isMoving() {
        return this.velocity.x() != 0 || this.velocity.y() != 0;
    }

    public void takeDamage(int damage) {
        int left = Math.max(health.value() - damage, 0);
        health = new Health(left);
    }
}

