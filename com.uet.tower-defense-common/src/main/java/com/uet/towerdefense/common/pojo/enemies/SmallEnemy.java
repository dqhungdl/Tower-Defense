package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class SmallEnemy extends AbstractEnemy {

    private static final String[] ENEMY_IMAGE_ID = {"245", "246", "247", "248"};

    public SmallEnemy(int level) {
        super(level);
        hp = Enemies.SMALL_HP[level];
        speed = Enemies.SMALL_SPEED[level];
        defense = Enemies.SMALL_DEFENSE[level];
        money = Enemies.SMALL_MONEY[level];
    }

    @Override
    public String getEnemyType() {
        return Enemies.SMALL;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID[level];
    }
}
