package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.pojo.base.BaseStaticEntity;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

import java.util.ArrayList;
import java.util.List;

public interface BaseTower<Tid extends Object> extends BaseStaticEntity<Tid> {

    List<BaseBullet> bullets = new ArrayList<>();

    double getSpeed();

    void setSpeed(double speed);

    int getRange();

    void setRange(int range);

    int getDamage();

    void setDamage(int damage);

    int getGold();

    void setGold(int gold);

    int getLevel();

    void setLevel(int level);

    void levelUp();

    int getDirection();

    void setDirection(int direction);

    long getLastFireTimestamp();

    void setLastFireTimestamp(long lastFireTimestamp);

    String getTowerType();

    String getStandImageId();

    String getTowerImageId();

    List<BaseBullet> getBullets();

    void setBullets(List<BaseBullet> bullets);

    void addBullet(BaseEnemy enemy);

    void init();

    void render(List<NodeCompare> nodes);

    void update(List<BaseEnemy> enemies, long currentTimestamp);
}
