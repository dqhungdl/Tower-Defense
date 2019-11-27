package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTower extends AbstractStaticEntity<Long> implements BaseTower<Long> {

    protected double speed;

    protected int range;

    protected int damage;

    protected int money;

    protected int level = 0;

    protected int direction;

    protected long lastFireTimestamp = 0;

    protected List<BaseBullet> bullets = new ArrayList<>();

    protected ImageView imageViewStand;

    protected ImageView imageViewTower;

    protected Circle rangeCircle;

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
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
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
        level++;
        for (BaseBullet bullet : bullets)
            bullet.setLevel(level);
        imageViewTower.setImage(AssetUtil.getTowerImage(getTowerImageId()));
    }

    @Override
    public ImageView getImageViewStand() {
        return imageViewStand;
    }

    @Override
    public void setImageViewStand(ImageView imageViewStand) {
        this.imageViewStand = imageViewStand;
    }

    @Override
    public ImageView getImageViewTower() {
        return imageViewTower;
    }

    @Override
    public void setImageViewTower(ImageView imageViewTower) {
        this.imageViewTower = imageViewTower;
    }

    private void addRangeCircle(List<Node> nodes) {
        for (Node node : nodes)
            if (node == rangeCircle)
                return;
        for (int i = 0; i < nodes.size(); i++)
            if (Integer.parseInt(nodes.get(i).getId()) > Integer.parseInt(rangeCircle.getId())) {
                nodes.add(i, rangeCircle);
                return;
            }
        nodes.add(rangeCircle);
    }

    private void removeRangeCircle(List<Node> nodes) {
        for (Node node : nodes)
            if (node == rangeCircle) {
                nodes.remove(node);
                break;
            }
    }

    private String getContent() {
        String content = "";
        if (getTowerType().equals(Towers.SNIPER))
            content += "Sniper\n";
        if (getTowerType().equals(Towers.MACHINE_GUN))
            content += "Machine Gun\n";
        if (getTowerType().equals(Towers.ROCKET))
            content += "Rocket\n";
        if (getTowerType().equals(Towers.AIR_GUN))
            content += "Air Gun";
        content += "Speed   : " + speed + "\n";
        content += "Range   : " + range + "\n";
        content += "Damage  : " + damage + "\n";
        if (level == 2)
            content += "Max level";
        else {
            int money = 0;
            if (getTowerType().equals(Towers.SNIPER))
                money = Towers.SNIPER_MONEY[level + 1];
            if (getTowerType().equals(Towers.MACHINE_GUN))
                money = Towers.MACHINE_GUN_MONEY[level + 1];
            if (getTowerType().equals(Towers.ROCKET))
                money = Towers.ROCKET_MONEY[level + 1];
            if (getTowerType().equals(Towers.AIR_GUN))
                money = Towers.AIR_GUN_MONEY[level + 1];
            content += "Level up: " + money;
        }
        return content;
    }

    public AbstractTower(double x, double y, List<Node> nodes, Text notification) {
        this.x = x;
        this.y = y;
        this.direction = 0;
        // Tower image
        imageViewStand = new ImageView(AssetUtil.getTowerImage(getStandImageId()));
        imageViewStand.setId(RenderLevels.TOWER_STAND);
        // Range circle
        rangeCircle = new Circle();
        rangeCircle.setCenterX(y + GamePlays.TOWER_SIZE / 2);
        rangeCircle.setCenterY(x + GamePlays.TOWER_SIZE / 2);
        rangeCircle.setFill(Color.BLUE);
        rangeCircle.setOpacity(Animations.LIGHT_OPACITY);
        rangeCircle.setId(RenderLevels.ANIMATION);
        imageViewStand.setOnMouseEntered(mouseEvent -> {
            imageViewStand.setOpacity(Animations.DARK_OPACITY);
            imageViewTower.setOpacity(Animations.DARK_OPACITY);
            addRangeCircle(nodes);
            notification.setText(getContent());
        });
        imageViewStand.setOnMouseExited(mouseEvent -> {
            imageViewStand.setOpacity(Animations.NORMAL_OPACITY);
            imageViewTower.setOpacity(Animations.NORMAL_OPACITY);
            removeRangeCircle(nodes);
            notification.setText("");
        });
        imageViewTower = new ImageView(AssetUtil.getTowerImage(getTowerImageId()));
        imageViewTower.setId(RenderLevels.TOWER);
        imageViewTower.setOnMouseEntered(mouseEvent -> {
            imageViewStand.setOpacity(Animations.DARK_OPACITY);
            imageViewTower.setOpacity(Animations.DARK_OPACITY);
            addRangeCircle(nodes);
            notification.setText(getContent());
        });
        imageViewTower.setOnMouseExited(mouseEvent -> {
            imageViewStand.setOpacity(Animations.NORMAL_OPACITY);
            imageViewTower.setOpacity(Animations.NORMAL_OPACITY);
            removeRangeCircle(nodes);
            notification.setText("");
        });
    }

    @Override
    public void render() {
        rangeCircle.setRadius(range);
        imageViewStand.setX(y);
        imageViewStand.setY(x);
        imageViewTower.setX(y);
        imageViewTower.setY(x);
        imageViewTower.setRotate(this.direction);
    }

    private boolean isCoincideTargetEnemy(List<BaseTower> towers, BaseEnemy enemy) {
        // Check coincide target enemy
        for (BaseTower tower : towers)
            if (tower != this) {
                List<BaseBullet> bullets = tower.getBullets();
                for (BaseBullet bullet : bullets)
                    if (bullet.getTargetEnemy() == enemy && bullet.getDamage() >= enemy.getHp())
                        return true;
            }
        return false;
    }

    @Override
    public void update(List<BaseEnemy> enemies, List<BaseTower> towers, long currentTimestamp) {
        double towerX = x + GamePlays.TOWER_SIZE / 2;
        double towerY = y + GamePlays.TOWER_SIZE / 2;
        int minDistance = 1000000000;
        BaseEnemy targetEnemy = null;
        for (BaseEnemy enemy : enemies) {
            if (isCoincideTargetEnemy(towers, enemy))
                continue;
            if ((!getTowerType().equals(Towers.AIR_GUN) && enemy.getEnemyType().equals(Enemies.PLANE))
                    || (getTowerType().equals(Towers.AIR_GUN) && !enemy.getEnemyType().equals(Enemies.PLANE)))
                continue;
            int distance = (int) Math.sqrt(Math.pow(enemy.getX() + GamePlays.ENEMY_SIZE / 2 - towerX, 2) + Math.pow(enemy.getY() + GamePlays.ENEMY_SIZE / 2 - towerY, 2));
            if (minDistance > distance) {
                minDistance = distance;
                targetEnemy = enemy;
            }
        }
        if (minDistance <= range && currentTimestamp - lastFireTimestamp >= GamePlays.SECOND_TO_MILLI * speed) {
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
