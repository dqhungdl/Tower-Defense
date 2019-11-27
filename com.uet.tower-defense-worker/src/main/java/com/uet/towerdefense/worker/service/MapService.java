package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.enums.*;
import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.Directions;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.util.AssetUtil;
import com.uet.towerdefense.worker.controller.SceneController;
import com.uet.towerdefense.worker.scene.GamePlayScene;
import com.uet.towerdefense.worker.scene.MenuScene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MapService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private SoundService soundService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SceneController sceneController;

    @Autowired
    private GamePlayScene gamePlayScene;

    private int mapId;
    private List<BaseEnemy> enemies = new ArrayList<>();
    private List<BaseTower> towers = new ArrayList<>();
    private List<Coordinate> paths = new ArrayList<>();

    public List<BaseEnemy> getEnemies() {
        return enemies;
    }

    public List<BaseTower> getTowers() {
        return towers;
    }

    public void init(int mapId) {
        this.mapId = mapId;
        ImageView imageView = new ImageView(AssetUtil.getMapImage(mapId));
        imageView.setId(RenderLevels.MAP);
        nodeService.add(imageView);
        findPath((int) Maps.MAP_SPAWNS[mapId].getX(), (int) Maps.MAP_SPAWNS[mapId].getY(), -1);
    }

    private void findPath(int x, int y, int lastDirection) {
        paths.add(new Coordinate(x, y));
        for (int i = 0; i < 4; i++)
            if ((i + 2) % 4 != lastDirection) {
                int newX = x + (int) Directions.VECTORS[i].getDx();
                int newY = y + (int) Directions.VECTORS[i].getDy();
                if (newX == Maps.MAP_TARGETS[mapId].getX() && newY == Maps.MAP_TARGETS[mapId].getY()) {
                    paths.add(new Coordinate(newX, newY));
                    return;
                }
                if (newX < 0 || newX >= GamePlays.HEIGHT || newY < 0 || newY >= GamePlays.WIDTH)
                    continue;
                if (Maps.MAP_SPRITES[mapId][newX][newY] == Cells.GRASS || Maps.MAP_SPRITES[mapId][newX][newY] == Cells.OBSTACLE)
                    continue;
                findPath(newX, newY, i);
                break;
            }
    }

    public void addEnemy(BaseEnemy enemy) {
        enemy.setX(Maps.MAP_SPAWNS[mapId].getX() * GamePlays.SPRITE_SIZE);
        enemy.setY(Maps.MAP_SPAWNS[mapId].getY() * GamePlays.SPRITE_SIZE);
        enemies.add(enemy);
    }

    public boolean addTower(BaseTower tower) {
        for (int i = (int) tower.getX(); i < tower.getX() + GamePlays.TOWER_SIZE; i++)
            for (int j = (int) tower.getY(); j < tower.getY() + GamePlays.TOWER_SIZE; j++) {
                Coordinate coordinate = new Coordinate(i / GamePlays.SPRITE_SIZE, j / GamePlays.SPRITE_SIZE);
                if (coordinate.getX() < 0 || coordinate.getX() >= GamePlays.HEIGHT || coordinate.getY() < 0 || coordinate.getY() >= GamePlays.WIDTH
                        || Maps.MAP_SPRITES[mapId][(int) coordinate.getX()][(int) coordinate.getY()] != Cells.GRASS)
                    return false;
            }
        for (BaseTower tempTower : towers) {
            double xMin = Math.max(tower.getX(), tempTower.getX());
            double yMin = Math.max(tower.getY(), tempTower.getY());
            double xMax = Math.min(tower.getX() + GamePlays.TOWER_SIZE - 1, tempTower.getX() + GamePlays.TOWER_SIZE - 1);
            double yMax = Math.min(tower.getY() + GamePlays.TOWER_SIZE - 1, tempTower.getY() + GamePlays.TOWER_SIZE - 1);
            if (xMin < xMax && yMin < yMax)
                return false;
        }
        towers.add(tower);
        return true;
    }

    public void buyTower(BaseTower tower) {
        if (!menuService.addMoney(-tower.getMoney())) {
            notificationService.setNotification("\n  Not enough \n    money");
            return;
        }
        double x = tower.getX(), y = tower.getY();
        for (int distance = 0; distance <= Towers.ACCEPTABLE_PLACED_RANGE; distance++) {
            for (int i = (int) x - distance; i <= x + distance; i++) {
                tower.setX(i);
                tower.setY(y - (distance - Math.abs(i - x)));
                if (addTower(tower))
                    return;
                tower.setX(i);
                tower.setY(y + (distance - Math.abs(i - x)));
                if (addTower(tower))
                    return;
            }
        }
        menuService.addMoney(tower.getMoney());
    }

    public void sellTower(BaseTower tower) {
        int money = 0;
        if (tower.getTowerType().equals(Towers.SNIPER))
            for (int i = 0; i <= tower.getLevel(); i++)
                money += Towers.SNIPER_MONEY[i];
        if (tower.getTowerType().equals(Towers.MACHINE_GUN))
            for (int i = 0; i <= tower.getLevel(); i++)
                money += Towers.MACHINE_GUN_MONEY[i];
        if (tower.getTowerType().equals(Towers.ROCKET))
            for (int i = 0; i <= tower.getLevel(); i++)
                money += Towers.ROCKET_MONEY[i];
        if (tower.getTowerType().equals(Towers.AIR_GUN))
            for (int i = 0; i <= tower.getLevel(); i++)
                money += Towers.AIR_GUN_MONEY[i];
        menuService.addMoney(money / 2);
        for (BaseTower tempTower : towers)
            if (tempTower == tower) {
                towers.remove(tempTower);
                List<BaseBullet> bullets = tempTower.getBullets();
                for (BaseBullet bullet : bullets)
                    nodeService.remove(bullet.getImageView());
                break;
            }
        nodeService.remove(tower.getImageViewStand());
        nodeService.remove(tower.getImageViewTower());
    }

    public void updateTower(BaseTower tower) {
        if (tower.getLevel() == 2)
            return;
        int money = 0;
        if (tower.getTowerType().equals(Towers.SNIPER))
            money = Towers.SNIPER_MONEY[tower.getLevel() + 1];
        if (tower.getTowerType().equals(Towers.MACHINE_GUN))
            money = Towers.MACHINE_GUN_MONEY[tower.getLevel() + 1];
        if (tower.getTowerType().equals(Towers.ROCKET))
            money = Towers.ROCKET_MONEY[tower.getLevel() + 1];
        if (tower.getTowerType().equals(Towers.AIR_GUN))
            money = Towers.AIR_GUN_MONEY[tower.getLevel() + 1];
        if (!menuService.addMoney(-money)) {
            notificationService.setNotification("\n  Not enough \n    money");
            return;
        }
        tower.levelUp();
    }

    public void killEnemy(BaseEnemy enemy) {
        menuService.addMoney(enemy.getMoney());
    }

    public void attackBase(BaseEnemy enemy) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH);
        rectangle.setHeight(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        rectangle.setOpacity(Animations.LIGHT_OPACITY);
        rectangle.setId(RenderLevels.COVER);
        rectangle.setOnMouseClicked(mouseEvent -> {
            sceneController.toMenuScene();
        });
        nodeService.add(rectangle);
        if (!menuService.subHp(enemy.getHp())) {
            notificationService.setNotification("\n  Defeat\nGo to Menu ...");
            gamePlayScene.getTimeline().stop();
        }
    }

    public void render() {
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets)
                bullet.render();
            tower.render();
        }
        for (BaseEnemy enemy : enemies)
            enemy.render();
    }

    public void update(long latestTimestamp) {
        // update
        for (BaseEnemy enemy : enemies) {
            enemy.update(paths, mapId);
            nodeService.add(enemy.getImageView());
        }
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets) {
                bullet.update();
                nodeService.add(bullet.getImageView());
            }
            tower.update(enemies, towers, latestTimestamp);
            if (tower.getLastFireTimestamp() == latestTimestamp)
                soundService.soundProduce("shoot");
            nodeService.add(tower.getImageViewStand());
            nodeService.add(tower.getImageViewTower());
        }
        // destroy
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getX() == bullets.get(i).getTargetEnemy().getX()
                        && bullets.get(i).getY() == bullets.get(i).getTargetEnemy().getY()) {
                    if (bullets.get(i).getBulletType().equals(Bullets.ROCKET))
                        for (BaseEnemy enemy : enemies) {
                            double distance = Math.sqrt(Math.pow(enemy.getX() - bullets.get(i).getX(), 2) + Math.pow(enemy.getY() - bullets.get(i).getY(), 2));
                            if (!enemy.getEnemyType().equals(Enemies.PLANE) && distance <= Bullets.ROCKET_EXPLOSION_RANGE[tower.getLevel()])
                                enemy.setHp(enemy.getHp() - bullets.get(i).getDamage());
                        }
                    else
                        bullets.get(i).getTargetEnemy().setHp(bullets.get(i).getTargetEnemy().getHp() - bullets.get(i).getDamage());
                    nodeService.remove(bullets.get(i).getImageView());
                    bullets.remove(i--);
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++)
            if ((enemies.get(i).getX() == paths.get(paths.size() - 1).getX() * GamePlays.SPRITE_SIZE
                    && enemies.get(i).getY() == paths.get(paths.size() - 1).getY() * GamePlays.SPRITE_SIZE)
                    || enemies.get(i).getHp() <= 0) {
                if (enemies.get(i).getHp() <= 0)
                    killEnemy(enemies.get(i));
                else
                    attackBase(enemies.get(i));
                nodeService.remove(enemies.get(i).getImageView());
                enemies.remove(i--);
            }
    }
}
