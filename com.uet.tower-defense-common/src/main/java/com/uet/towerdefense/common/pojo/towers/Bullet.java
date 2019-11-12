package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import javafx.scene.Group;

public class Bullet extends AbstractDynamicEntity<Long> implements BaseBullet<Long> {

    private double speed;

    private int damage;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void render(Group group) {

    }

    @Override
    public void update() {

    }
}
