package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class AirGunBullet extends AbstractBullet {

    private static final String[] BULLET_IMAGE_ID = new String[]{"303", "304", "252"};

    public AirGunBullet(double x, double y, int direction, int damage, int level, BaseEnemy targetEnemy) {
        super(x, y, direction, damage, level, targetEnemy);
        this.speed = Bullets.AIR_GUN_SPEED;
    }

    @Override
    public String getBulletType() {
        return Bullets.AIR_GUN;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID[level];
    }
}
