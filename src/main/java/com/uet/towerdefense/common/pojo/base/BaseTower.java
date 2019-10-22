package com.uet.towerdefense.common.pojo.base;

public interface BaseTower extends StaticEntity {

    int getSpeed();

    void setSpeed(int speed);

    int getRange();

    void setRange(int range);

    int getDamage();

    void setDamage(int damage);

    int getGold();

    void setGold(int gold);
}
