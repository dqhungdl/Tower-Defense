package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

import java.util.List;

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

    void render(List<NodeCompare> nodes);

    void update();
}