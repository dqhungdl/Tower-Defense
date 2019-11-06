package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class TankerEnemy extends AbstractEnemy {

    @Override
    public String getEnemyType() {
        return Enemies.TANKER;
    }
}
