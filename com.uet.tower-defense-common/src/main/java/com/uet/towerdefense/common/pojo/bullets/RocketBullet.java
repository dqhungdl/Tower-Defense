package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class RocketBullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID[] = {"305", "306", "251"};

    public RocketBullet(double x, double y, int direction, int damage, int level, BaseEnemy targetEnemy) {
        super(x, y, direction, damage, level, targetEnemy);
        this.speed = Bullets.ROCKET_SPEED;
    }

    @Override
    public String getBulletType() {
        return Bullets.ROCKET;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID[level];
    }
}
