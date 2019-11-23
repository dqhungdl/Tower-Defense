package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.MachineGunBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class MachineGunTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "182";
    private static final String[] TOWER_IMAGE_ID = new String[]{"203", "203"};

    public MachineGunTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
        speed = Towers.MACHINE_GUN_SPEED[level];
        range = Towers.MACHINE_GUN_RANGE[level];
        damage = Towers.MACHINE_GUN_DAMAGE[level];
        gold = Towers.MACHINE_GUN_GOLD[level];
        init();
    }

    @Override
    public String getTowerType() {
        return Towers.MACHINE_GUN;
    }

    @Override
    public String getStandImageId() {
        return STAND_IMAGE_ID;
    }

    @Override
    public String getTowerImageId() {
        return TOWER_IMAGE_ID[level];
    }

    @Override
    public void addBullet(BaseEnemy targetEnemy) {
        bullets.add(new MachineGunBullet(x, y, direction, damage, targetEnemy));
    }
}
