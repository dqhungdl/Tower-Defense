package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

import java.util.List;

public class SniperBullet extends AbstractBullet {

    private static final String[] BULLET_IMAGE_ID = {"295", "295", "296"};

    public SniperBullet(double x, double y, int direction, int damage, int level, BaseEnemy targetEnemy) {
        super(x, y, direction, damage, level, targetEnemy);
        this.speed = Bullets.SNIPER_SPEED;
    }

    @Override
    public String getBulletType() {
        return Bullets.SNIPER;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID[level];
    }
}
