package com.uet.towerdefense.common.pojo.bullets;

import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet extends AbstractDynamicEntity<Long> implements BaseBullet<Long> {

    protected double speed;

    protected int damage;

    protected int level;

    protected int direction;

    protected BaseEnemy targetEnemy;

    protected ImageView imageView;

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

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
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
    public void levelUp() {
        if (level == 2)
            return;
        level++;
        imageView = new ImageView(AssetUtil.getBulletImage(getBulletImageId()));
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public AbstractBullet(double x, double y, int direction, int damage, int level, BaseEnemy targetEnemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.damage = damage;
        this.level = level;
        this.targetEnemy = targetEnemy;
        imageView = new ImageView(AssetUtil.getBulletImage(getBulletImageId()));
        imageView.setId(RenderLevels.BULLET);
    }

    @Override
    public void render() {
        imageView.setX(y);
        imageView.setY(x);
        imageView.setRotate(direction);

    }

    @Override
    public void update() {
        Vector v1 = new Vector(0, 1);
        Vector v2 = new Vector(targetEnemy.getX() - x, targetEnemy.getY() - y);
        double distance1 = Math.sqrt(Math.pow(v1.getDx(), 2) + Math.pow(v1.getDy(), 2));
        double distance2 = Math.sqrt(Math.pow(v2.getDx(), 2) + Math.pow(v2.getDy(), 2));
        double angle = Math.toDegrees(Math.acos((v1.getDx() * v2.getDy() + v1.getDy() * v2.getDx()) / (distance1 * distance2)));
        if (v2.getDy() > 0)
            angle = 360.0 - angle;
        angle += 180.0;
        direction = (int) angle;
        double distance = Math.sqrt(Math.pow(targetEnemy.getX() - x, 2) + Math.pow(targetEnemy.getY() - y, 2));
        if (distance <= speed) {
            x = targetEnemy.getX();
            y = targetEnemy.getY();
        } else {
            y += speed * Math.cos(Math.toRadians(Math.abs(direction - 90.0)));
            x += speed * Math.sin(Math.toRadians(Math.abs(direction - 90.0)));
        }
    }
}
