package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;
import javafx.scene.canvas.GraphicsContext;

public interface BaseEnemy<Tid extends Object> extends BaseDynamicEntity<Tid> {

    int getHp();

    void setHp(int hp);

    int getSpeed();

    void setSpeed(int speed);

    int getDefense();

    void setDefense(int defense);

    int getGold();

    void setGold(int gold);

    double getDirection();

    void setDirection(double direction);

    String getEnemyType();

    String getEnemyImageId();

    void render(GraphicsContext graphicsContext);

    void update();
}
