package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class TankEnemy extends AbstractEnemy {

    private static final String[] ENEMY_IMAGE_ID = {"300", "301"};

    public TankEnemy(int level) {
        super(level);
        hp = Enemies.TANK_HP[level];
        speed = Enemies.TANK_SPEED[level];
        defense = Enemies.TANK_DEFENSE[level];
        money = Enemies.TANK_MONEY[level];
    }

    @Override
    public String getEnemyType() {
        return Enemies.TANK;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID[level];
    }
}
