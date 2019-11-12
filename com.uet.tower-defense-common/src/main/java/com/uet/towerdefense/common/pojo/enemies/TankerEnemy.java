package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Pair;
import com.uet.towerdefense.common.enums.Enemies;
import javafx.scene.Group;

import java.util.List;

public class TankerEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.TANKER;
    }

    @Override
    public String getEnemyImageId() {
        return null;
    }

    @Override
    public void render(Group group) {

    }

    @Override
    public void update(List<Pair> paths) {

    }
}
