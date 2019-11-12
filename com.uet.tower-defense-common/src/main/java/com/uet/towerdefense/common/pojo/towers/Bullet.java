package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Bullets;
import javafx.scene.Group;

public class Bullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID = "295";

    public Bullet(int x, int y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = Bullets.SPEED;
        this.damage = Bullets.DAMAGE;
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

    @Override
    public void render(Group group) {

    }

    @Override
    public void update() {

    }
}
