package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;

public interface BaseEnemy<Tid extends Object> extends BaseDynamicEntity<Tid> {

    int getHp();

    void setHp(int hp);

    double getSpeed();

    void setSpeed(double speed);

    int getDefense();

    void setDefense(int defense);

    int getGold();

    void setGold(int gold);

    double getDirection();

    void setDirection(double direction);

    String getEnemyType();

    String getEnemyImageId();
}
