package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class MachineGunBullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID = "272";

    public MachineGunBullet(double x, double y, int direction, int damage, BaseEnemy targetEnemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.damage = damage;
        this.speed = Bullets.MACHINE_GUN_SPEED;
        this.targetEnemy = targetEnemy;
        init();
    }

    @Override
    public String getBulletType() {
        return Bullets.MACHINE_GUN;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID;
    }
}
