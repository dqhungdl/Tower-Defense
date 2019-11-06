package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.BaseEntity;

public interface BaseBullet<Tid extends Object> extends BaseEntity<Tid> {

    int getSpeed();

    void setSpeed(int speed);

    int getDamage();

    void setDamage(int damage);
}