package com.hao.demo.ddd.domain.components.move;

import com.hao.demo.ddd.dp.Transform;
import com.hao.demo.ddd.dp.Vector;

public interface Movable {
    // 相当于组件
    Transform getPosition();
    Vector getVelocity();

    // 行为
    void moveTo(long x, long y);
    void startMove(long velX, long velY);
    void stopMove();
    boolean isMoving();
}

