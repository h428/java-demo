package com.hao.demo.ddd.domain.components.move;

import com.hao.demo.ddd.dp.Transform;
import com.hao.demo.ddd.dp.Vector;
import java.util.ArrayList;
import java.util.List;

public class MovementSystem {

    private static final long X_FENCE_MIN = -100;
    private static final long X_FENCE_MAX = 100;
    private static final long Y_FENCE_MIN = -100;
    private static final long Y_FENCE_MAX = 100;

    private List<Movable> entities = new ArrayList<>();

    public void register(Movable movable) {
        entities.add(movable);
    }

    public void update() {
        for (Movable entity : entities) {
            if (!entity.isMoving()) {
                continue;
            }

            Transform old = entity.getPosition();
            Vector vel = entity.getVelocity();
            long newX = Math.max(Math.min(old.x() + vel.x(), X_FENCE_MAX), X_FENCE_MIN);
            long newY = Math.max(Math.min(old.y() + vel.y(), Y_FENCE_MAX), Y_FENCE_MIN);
            entity.moveTo(newX, newY);
        }
    }
}

