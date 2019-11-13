package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class NormalBullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID = "275";

    public NormalBullet(int x, int y, int direction, int damage, BaseEnemy targetEnemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.damage = damage;
        this.speed = Bullets.SPEED;
        this.targetEnemy = targetEnemy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
