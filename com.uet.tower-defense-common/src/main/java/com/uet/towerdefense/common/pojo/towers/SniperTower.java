package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;

public class SniperTower extends AbstractTower {

    @Override
    public String getTowerType() {
        return Towers.SNIPER;
    }
}
