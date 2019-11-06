package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class NormalEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.NORMAL;
    }
}
