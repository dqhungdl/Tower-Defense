package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class PlaneEnemy extends AbstractEnemy {

    private static final String[] ENEMY_IMAGE_ID = {"270", "271"};

    public PlaneEnemy(int level) {
        super(level);
        hp = Enemies.PLANE_HP[level];
        speed = Enemies.PLANE_SPEED[level];
        defense = Enemies.PLANE_DEFENSE[level];
        money = Enemies.PLANE_MONEY[level];
    }

    @Override
    public String getEnemyType() {
        return Enemies.PLANE;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID[level];
    }
}
