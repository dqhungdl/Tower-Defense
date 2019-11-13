package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Times;
import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.bullets.NormalBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    protected int speed;

    protected int range;

    protected int damage;

    protected int gold;

    protected int level = 0;

    protected int direction;

    protected long lastFireTimestamp = 0;

    protected List<BaseBullet> bullets = new ArrayList<>();

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
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
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public long getLastFireTimestamp() {
        return lastFireTimestamp;
    }

    @Override
    public void setLastFireTimestamp(long lastFireTimestamp) {
        this.lastFireTimestamp = lastFireTimestamp;
    }

    @Override
    public List<BaseBullet> getBullets() {
        return bullets;
    }

    @Override
    public void setBullets(List<BaseBullet> bullets) {
        this.bullets = bullets;
    }

    @Override
    public void levelUp() {
        if (level == 1)
            return;
        level++;
    }

    @Override
    public void render(Group group) {
        ImageView imageViewStand = new ImageView(AssetUtil.getTowerImage(getStandImageId()));
        ImageView imageViewTower = new ImageView(AssetUtil.getTowerImage(getTowerImageId()));
        imageViewStand.setX(y);
        imageViewStand.setY(x);
        imageViewTower.setX(y);
        imageViewTower.setY(x);
        imageViewTower.setRotate(this.direction);
        group.getChildren().add(imageViewStand);
        group.getChildren().add(imageViewTower);
    }

    @Override
    public void update(List<BaseEnemy> enemies, long currentTimestamp) {
        int towerX = x + GamePlays.TOWER_SIZE / 2;
        int towerY = y + GamePlays.TOWER_SIZE / 2;
        int minDistance = 1000000000;
        BaseEnemy targetEnemy = null;
        for (BaseEnemy enemy : enemies) {
            int distance = (int) Math.sqrt(Math.pow(enemy.getX() - towerX, 2) + Math.pow(enemy.getY() - towerY, 2));
            if (minDistance > distance) {
                minDistance = distance;
                targetEnemy = enemy;
            }
        }
        if (minDistance <= range) {
            Vector v1 = new Vector(0, 1);
            Vector v2 = new Vector(targetEnemy.getX() - towerX, targetEnemy.getY() - towerY);
            double distance1 = Math.sqrt(Math.pow(v1.getDx(), 2) + Math.pow(v1.getDy(), 2));
            double distance2 = Math.sqrt(Math.pow(v2.getDx(), 2) + Math.pow(v2.getDy(), 2));
            double angle = Math.toDegrees(Math.acos((double) (v1.getDx() * v2.getDy() + v1.getDy() * v2.getDx()) / (distance1 * distance2)));
            if (v2.getDy() > 0)
                angle = 360.0 - angle;
            angle += 180.0;
            direction = (int) angle % 360;
            if (currentTimestamp - lastFireTimestamp >= Times.SECOND_TO_NANO * speed) {
                lastFireTimestamp = currentTimestamp;
                bullets.add(new NormalBullet(x, y, direction, damage, targetEnemy));
            }
        }
    }
}
