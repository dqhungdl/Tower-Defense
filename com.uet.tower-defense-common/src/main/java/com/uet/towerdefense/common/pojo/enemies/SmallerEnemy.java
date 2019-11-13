package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class SmallerEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.SMALLER;
    }

    @Override
    public String getEnemyImageId() {
        return null;
    }
}
