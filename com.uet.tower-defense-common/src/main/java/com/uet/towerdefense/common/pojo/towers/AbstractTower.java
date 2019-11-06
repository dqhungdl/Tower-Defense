package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    private int speed;

    private int range;

    private int damage;

    private int gold;

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }
}
