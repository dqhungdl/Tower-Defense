package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

import java.util.List;

public class NormalBullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID = "295";

    public NormalBullet(double x, double y, int direction, int damage, BaseEnemy targetEnemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.damage = damage;
        this.speed = Bullets.NORMAL_SPEED;
        this.targetEnemy = targetEnemy;
        init();
    }

    @Override
    public String getBulletType() {
        return Bullets.NORMAL;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID;
    }
}
