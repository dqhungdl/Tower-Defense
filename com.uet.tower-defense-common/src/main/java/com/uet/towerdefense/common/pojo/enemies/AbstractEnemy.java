package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class AbstractEnemy extends AbstractDynamicEntity<Long> implements BaseEnemy<Long> {

    protected int hp;

    protected double speed;

    protected int defense;

    protected int money;

    protected int direction;

    protected int level;

    protected Vector vector;

    protected ImageView imageView;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
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
    public Vector getVector() {
        return vector;
    }

    @Override
    public void setVector(Vector vector) {
        this.vector = vector;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public AbstractEnemy(int level) {
        this.level = level;
        imageView = new ImageView(AssetUtil.getEnemyImage(getEnemyImageId()));
        imageView.setId(RenderLevels.ENEMY);
    }

    @Override
    public void render() {
        imageView.setX(y);
        imageView.setY(x);
        imageView.setRotate(this.direction);
    }

    @Override
    public void update(List<Coordinate> paths, int mapId) {
        if (getEnemyType().equals(Enemies.PLANE)) {
            double targetX = Maps.MAP_TARGETS[mapId].getX() * GamePlays.SPRITE_SIZE;
            double targetY = Maps.MAP_TARGETS[mapId].getY() * GamePlays.SPRITE_SIZE;
            Vector v1 = new Vector(0, 1);
            Vector v2 = new Vector(targetX - x, targetY - y);
            double distance1 = Math.sqrt(Math.pow(v1.getDx(), 2) + Math.pow(v1.getDy(), 2));
            double distance2 = Math.sqrt(Math.pow(v2.getDx(), 2) + Math.pow(v2.getDy(), 2));
            double angle = Math.toDegrees(Math.acos((v1.getDx() * v2.getDy() + v1.getDy() * v2.getDx()) / (distance1 * distance2)));
            if (v2.getDy() > 0)
                angle = 360.0 - angle;
            angle += 180.0;
            direction = (int) angle;
            double distance = Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
            if (distance <= speed) {
                x = targetX;
                y = targetY;
            } else {
                y += speed * Math.cos(Math.toRadians(Math.abs(direction - 90.0)));
                x += speed * Math.sin(Math.toRadians(Math.abs(direction - 90.0)));
            }
            return;
        }
        for (int i = paths.size() - 2; i >= 0; i--) {
            double startX = paths.get(i).getX() * GamePlays.SPRITE_SIZE;
            double startY = paths.get(i).getY() * GamePlays.SPRITE_SIZE;
            double endX = paths.get(i + 1).getX() * GamePlays.SPRITE_SIZE;
            double endY = paths.get(i + 1).getY() * GamePlays.SPRITE_SIZE;
            if (Math.min(startX, endX) <= x && x <= Math.max(startX, endX) && Math.min(startY, endY) <= y && y <= Math.max(startY, endY)) {
                vector = new Vector((endX - startX == 0 ? 0 : endX > startX ? 1 : -1), (endY - startY == 0 ? 0 : endY > startY ? 1 : -1));
                if (vector.equals(new Vector(0, 1)))
                    direction = 0;
                if (vector.equals(new Vector(1, 0)))
                    direction = 90;
                if (vector.equals(new Vector(0, -1)))
                    direction = 180;
                if (vector.equals(new Vector(-1, 0)))
                    direction = 270;
                x += vector.getDx() * speed;
                y += vector.getDy() * speed;
                if (Math.min(startX, endX) > x || x > Math.max(startX, endX) || Math.min(startY, endY) > y || y > Math.max(startY, endY)) {
                    x = endX;
                    y = endY;
                }
                break;
            }
        }
    }
}
