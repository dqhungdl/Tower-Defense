package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.BaseStaticEntity;

public interface BaseTower<Tid extends Object> extends BaseStaticEntity<Tid> {

    double getSpeed();

    void setSpeed(double speed);

    double getRange();

    void setRange(double range);

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
