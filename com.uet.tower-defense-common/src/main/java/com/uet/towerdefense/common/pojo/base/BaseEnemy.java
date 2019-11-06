package com.uet.towerdefense.common.pojo.base;

public interface BaseEnemy extends BaseDynamicEntity {

    int getHp();

    void setHp(int hp);

    int getSpeed();

    void setSpeed(int speed);

    int getDefense();

    void setDefense(int defense);

    int getGold();

    void setGold(int gold);
}
