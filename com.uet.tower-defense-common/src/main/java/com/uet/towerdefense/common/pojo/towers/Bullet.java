package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;

public class Bullet extends AbstractDynamicEntity<Long> implements BaseBullet<Long> {

    private int speed;

    private int damage;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
