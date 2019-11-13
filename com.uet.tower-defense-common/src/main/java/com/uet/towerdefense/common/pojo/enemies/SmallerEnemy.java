package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.enums.Enemies;
import javafx.scene.Group;

import java.util.List;

public class SmallerEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.SMALLER;
    }

    @Override
    public String getEnemyImageId() {
        return null;
    }

    @Override
    public void render(Group group) {

    }

    @Override
    public void update(List<Coordinate> paths) {

    }
}
