package com.uet.towerdefense.common.pojo.base;

public abstract class AbstractTower implements BaseTower {

    private int speed;

    private int range;

    private int damage;

    private int gold;

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public void setRange(int range) {

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void setDamage(int damage) {

    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public void setGold(int gold) {

    }
}
