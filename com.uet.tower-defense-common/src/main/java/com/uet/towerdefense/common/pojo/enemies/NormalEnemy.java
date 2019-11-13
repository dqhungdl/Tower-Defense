package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;

public class NormalEnemy extends AbstractEnemy {

    private static final String ENEMY_IMAGE_ID = "245";

    public NormalEnemy() {
        hp = Enemies.NORMAL_HP;
        speed = Enemies.NORMAL_SPEED;
        defense = Enemies.NORMAL_DEFENSE;
        gold = Enemies.NORMAL_GOLD;
    }

    @Override
    public String getEnemyType() {
        return Enemies.NORMAL;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID;
    }
}
