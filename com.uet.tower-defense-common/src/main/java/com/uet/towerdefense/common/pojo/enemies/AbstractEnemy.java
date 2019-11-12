package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Pair;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class AbstractEnemy extends AbstractDynamicEntity<Long> implements BaseEnemy<Long> {

    protected int hp;

    protected int speed;

    protected int defense;

    protected int gold;

    protected int direction;

    protected Vector vector;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
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
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
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
    public void render(Group group) {
        ImageView imageView = new ImageView(AssetUtil.getEnemyImage(getEnemyImageId()));
        imageView.setX(y - GamePlays.SPRITE_SIZE / 2);
        imageView.setY(x - GamePlays.SPRITE_SIZE / 2);
        imageView.setRotate(this.direction);
        group.getChildren().add(imageView);
    }

    @Override
    public void update(List<Pair> paths) {
        for (int i = paths.size() - 2; i >= 0; i--) {
            int startX = paths.get(i).getX() * GamePlays.SPRITE_SIZE;
            int startY = paths.get(i).getY() * GamePlays.SPRITE_SIZE;
            int endX = paths.get(i + 1).getX() * GamePlays.SPRITE_SIZE;
            int endY = paths.get(i + 1).getY() * GamePlays.SPRITE_SIZE;
            if (Math.min(startX, endX) <= x && x <= Math.max(startX, endX) && Math.min(startY, endY) <= y && y <= Math.max(startY, endY)) {
                Vector vector = new Vector((endX - startX == 0 ? 0 : endX > startX ? 1 : -1), (endY - startY == 0 ? 0 : endY > startY ? 1 : -1));
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
