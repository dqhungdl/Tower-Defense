package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import javafx.scene.Group;

public interface BaseBullet<Tid extends Object> extends BaseDynamicEntity<Tid> {

    double getSpeed();

    void setSpeed(double speed);

    int getDamage();

    void setDamage(int damage);

    int getDirection();

    void setDirection(int direction);

    BaseEnemy getTargetEnemy();

    void setTargetEnemy(BaseEnemy enemy);

    String getBulletType();

    String getBulletImageId();

    void render(Group group);

    void update();
}