package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;

public class MachineGunBullet extends AbstractBullet {

    private static final String[] BULLET_IMAGE_ID = new String[]{"274", "275", "272"};

    public MachineGunBullet(double x, double y, int direction, int damage, int level, BaseEnemy targetEnemy) {
        super(x, y, direction, damage, level, targetEnemy);
        this.speed = Bullets.MACHINE_GUN_SPEED;
    }

    @Override
    public String getBulletType() {
        return Bullets.MACHINE_GUN;
    }

    @Override
    public String getBulletImageId() {
        return BULLET_IMAGE_ID[level];
    }
}
