package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;
import javafx.scene.Group;

public class BossEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.BOSS;
    }

    @Override
    public String getEnemyImageId() {
        return null;
    }

    @Override
    public void render(Group group) {

    }

    @Override
    public void update() {

    }
}
