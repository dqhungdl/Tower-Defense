package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.pojo.base.BaseEntity;
import javafx.scene.Group;

public interface BaseBullet<Tid extends Object> extends BaseEntity<Tid> {

    double getSpeed();

    void setSpeed(double speed);

    int getDamage();

    void setDamage(int damage);

    String getBulletType();

    String getBulletImageId();

    void render(Group group);

    void update();
}