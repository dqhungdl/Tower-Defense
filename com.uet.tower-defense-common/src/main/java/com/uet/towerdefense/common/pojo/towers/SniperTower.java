package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class SniperTower extends AbstractTower {

    @Override
    public String getTowerType() {
        return Towers.SNIPER;
    }

    @Override
    public String getStandImageId() {
        return null;
    }

    @Override
    public String getTowerImageId() {
        return null;
    }

    @Override
    public void addBullet(BaseEnemy enemy) {

    }
}
