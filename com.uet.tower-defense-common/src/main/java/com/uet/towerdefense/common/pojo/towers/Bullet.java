package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Bullets;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Bullet extends AbstractBullet {

    private static final String BULLET_IMAGE_ID = "295";
    public Bullet(int x, int y, double direction)
    {
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
        return null;
    }

    @Override
    public String getBulletImageId() {
        return null;
    }

    @Override
    public void render(Group group) {
        ImageView imageView = new ImageView(AssetUtil.getBulletImage(BULLET_IMAGE_ID));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setRotate(this.direction);
        group.getChildren().addAll(imageView);
    }

    @Override
    public void update() {
        x++;
        y++;
    }
}
