package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;

public class MachineGunTower extends AbstractTower {

    @Override
    public String getTowerType() {
        return Towers.MACHINE_GUN;
    }

    @Override
    public String getStandImageId() {
        return null;
    }

    @Override
    public String getTowerImageId() {
        return null;
    }
}
