package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    protected int speed;

    protected int range;

    protected int damage;

    protected int gold;

    protected int level = 0;

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
