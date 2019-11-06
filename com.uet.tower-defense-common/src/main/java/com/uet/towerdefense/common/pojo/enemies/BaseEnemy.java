package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;

public interface BaseEnemy<Tid extends Object> extends BaseDynamicEntity<Tid> {

    int getHp();

    void setHp(int hp);

    int getSpeed();

    void setSpeed(int speed);

    int getDefense();

    void setDefense(int defense);

    int getGold();

    void setGold(int gold);

    String getEnemyType();
}
