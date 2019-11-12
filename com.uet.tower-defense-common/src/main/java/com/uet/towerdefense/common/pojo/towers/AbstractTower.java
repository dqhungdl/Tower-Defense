package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    protected double speed;

    protected  double direction;

    protected double range;

    protected int damage;

    protected int gold;

    protected int level = 0;

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public void setRange(double range) {
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

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void levelUp() {
        if (level == 1)
            return;
        level++;
    }
}
