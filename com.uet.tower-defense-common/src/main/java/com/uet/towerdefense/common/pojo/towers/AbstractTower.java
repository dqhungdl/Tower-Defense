package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.bullets.NormalBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.event.EventHandler;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    protected double speed;

    protected int range;

    protected int damage;

    protected int gold;

    protected int level = 0;

    protected int direction;

    protected long lastFireTimestamp = 0;

    protected ImageView imageViewStand;

    protected ImageView imageViewTower;

    protected Double opacity = Animations.NORMAL_OPACITY;

    protected List<BaseBullet> bullets = new ArrayList<>();

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
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
    public void init() {
        imageViewStand = new ImageView(AssetUtil.getTowerImage(getStandImageId()));
        imageViewStand.setId(RenderLevels.TOWER_STAND);
        imageViewTower = new ImageView(AssetUtil.getTowerImage(getTowerImageId()));
        imageViewTower.setId(RenderLevels.TOWER);
    }

    @Override
    public void render(List<NodeCompare> nodes) {
        imageViewStand.setX(y);
        imageViewStand.setY(x);
        imageViewStand.setOpacity(opacity);
        imageViewTower.setX(y);
        imageViewTower.setY(x);
        imageViewTower.setOpacity(opacity);
        imageViewTower.setRotate(this.direction);
        nodes.add(new NodeCompare(imageViewStand));
        nodes.add(new NodeCompare(imageViewTower));
    }

    @Override
    public void update(List<BaseEnemy> enemies, long currentTimestamp) {
        int towerX = x + GamePlays.TOWER_SIZE / 2;
        int towerY = y + GamePlays.TOWER_SIZE / 2;
        int minDistance = 1000000000;
        BaseEnemy targetEnemy = null;
        for (BaseEnemy enemy : enemies) {
            int distance = (int) Math.sqrt(Math.pow(enemy.getX() + GamePlays.ENEMY_SIZE / 2 - towerX, 2) + Math.pow(enemy.getY() + GamePlays.ENEMY_SIZE / 2 - towerY, 2));
            if (minDistance > distance) {
                minDistance = distance;
                targetEnemy = enemy;
            }
        }
        if (minDistance <= range && currentTimestamp - lastFireTimestamp >= GamePlays.SECOND_TO_NANO * speed) {
            Vector v1 = new Vector(0, 1);
            Vector v2 = new Vector(targetEnemy.getX() + GamePlays.SPRITE_SIZE / 2 - towerX, targetEnemy.getY() + GamePlays.SPRITE_SIZE / 2 - towerY);
            double distance1 = Math.sqrt(Math.pow(v1.getDx(), 2) + Math.pow(v1.getDy(), 2));
            double distance2 = Math.sqrt(Math.pow(v2.getDx(), 2) + Math.pow(v2.getDy(), 2));
            double angle = Math.toDegrees(Math.acos((double) (v1.getDx() * v2.getDy() + v1.getDy() * v2.getDx()) / (distance1 * distance2)));
            if (v2.getDy() > 0)
                angle = 360.0 - angle;
            angle += 180.0;
            direction = (int) angle % 360;
            lastFireTimestamp = currentTimestamp;
            addBullet(targetEnemy);
        }
    }
}
