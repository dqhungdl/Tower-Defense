package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.BaseStaticEntity;

public interface BaseTower<Tid extends Object> extends BaseStaticEntity<Tid> {

    int getSpeed();

    void setSpeed(int speed);

    int getRange();

    void setRange(int range);

    int getDamage();

    void setDamage(int damage);

    int getGold();

    void setGold(int gold);

    int getLevel();

    void setLevel(int level);

    void levelUp();

    String getTowerType();

    String getStandImageId();

    String getTowerImageId();
}
