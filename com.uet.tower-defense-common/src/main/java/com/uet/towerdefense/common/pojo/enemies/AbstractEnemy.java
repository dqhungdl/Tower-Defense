package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;

public abstract class AbstractEnemy extends AbstractDynamicEntity<Long> implements BaseEnemy<Long> {

    private int hp;

    private int speed;

    private int defense;

    private int gold;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
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
