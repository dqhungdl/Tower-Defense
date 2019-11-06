package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;

public class NormalTower extends AbstractTower {

    @Override
    public String getTowerType() {
        return Towers.NORMAL;
    }
}
