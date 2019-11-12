package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;
import javafx.scene.canvas.GraphicsContext;

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
    public void render(GraphicsContext graphicsContext) {

    }

    @Override
    public void update() {

    }
}
