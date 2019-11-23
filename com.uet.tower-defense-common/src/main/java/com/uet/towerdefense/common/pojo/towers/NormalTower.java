package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.bullets.NormalBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class NormalTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "180";
    private static final String[] TOWER_IMAGE_ID = new String[]{"249", "250"};

    public NormalTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
        speed = Towers.NORMAL_SPEED[level];
        range = Towers.NORMAL_RANGE[level];
        damage = Towers.NORMAL_DAMAGE[level];
        gold = Towers.NORMAL_GOLD[level];
    }

    @Override
    public String getTowerType() {
        return Towers.NORMAL;
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
        bullets.add(new NormalBullet(x, y, direction, damage, targetEnemy));
    }
}
