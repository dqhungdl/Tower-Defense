package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet extends AbstractDynamicEntity<Long> implements BaseBullet<Long> {

    protected double speed;

    protected int damage;

    protected int direction;

    protected BaseEnemy targetEnemy;

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public BaseEnemy getTargetEnemy() {
        return targetEnemy;
    }

    @Override
    public void setTargetEnemy(BaseEnemy targetEnemy) {
        this.targetEnemy = targetEnemy;
    }

    @Override
    public void render(Group group) {
        ImageView imageView = new ImageView(AssetUtil.getBulletImage(getBulletImageId()));
        imageView.setX(y);
        imageView.setY(x);
        imageView.setRotate(direction);
        group.getChildren().add(imageView);
    }

    @Override
    public void update() {
        int targetX = targetEnemy.getX() + targetEnemy.getVector().getDx() * targetEnemy.getSpeed();
        int targetY = targetEnemy.getY() + targetEnemy.getVector().getDy() * targetEnemy.getSpeed();
        x += (targetX - x) / speed;
        y += (targetY - y) / speed;
        double distance = Math.sqrt(Math.pow(targetEnemy.getX() - x, 2) + Math.pow(targetEnemy.getY() - y, 2));
        if (distance <= Bullets.ACCEPTED_RANGE) {
            x = targetEnemy.getX();
            y = targetEnemy.getY();
        }
        speed = Math.max(speed - Bullets.ACCELERATION, 1);
    }
}
