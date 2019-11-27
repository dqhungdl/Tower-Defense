package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.pojo.base.BaseDynamicEntity;
import javafx.scene.image.ImageView;

import java.util.List;

public interface BaseEnemy<Tid extends Object> extends BaseDynamicEntity<Tid> {

    int getHp();

    void setHp(int hp);

    double getSpeed();

    void setSpeed(double speed);

    int getDefense();

    void setDefense(int defense);

    int getMoney();

    void setMoney(int money);

    int getDirection();

    void setDirection(int direction);

    Vector getVector();

    void setVector(Vector vector);

    ImageView getImageView();

    void setImageView(ImageView imageView);

    String getEnemyType();

    String getEnemyImageId();

    void render();

    void update(List<Coordinate> paths, int mapId);
}
