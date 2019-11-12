package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Pair;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;
import javafx.scene.Group;

import java.util.List;

public interface BaseEnemy<Tid extends Object> extends BaseDynamicEntity<Tid> {

    int getHp();

    void setHp(int hp);

    int getSpeed();

    void setSpeed(int speed);

    int getDefense();

    void setDefense(int defense);

    int getGold();

    void setGold(int gold);

    int getDirection();

    void setDirection(int direction);

    Vector getVector();

    void setVector(Vector vector);

    String getEnemyType();

    String getEnemyImageId();

    void render(Group group);

    void update(List<Pair> paths);
}
