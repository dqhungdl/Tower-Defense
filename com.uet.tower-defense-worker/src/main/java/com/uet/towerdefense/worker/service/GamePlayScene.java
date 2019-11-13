package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.enums.graphics.Directions;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.enums.graphics.Times;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.NormalEnemy;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamePlayScene {

    private Scene scene;
    private Group group;
    private int mapId;
    private long latestTimestamp;
    private List<BaseEnemy> enemies = new ArrayList<>();
    private List<BaseTower> towers = new ArrayList<>();
    private List<Coordinate> paths = new ArrayList<>();

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void findPath(int x, int y, int lastDirection) {
        paths.add(new Coordinate(x, y));
        for (int i = 0; i < 4; i++)
            if ((i + 2) % 4 != lastDirection) {
                int newX = x + Directions.VECTORS[i].getDx();
                int newY = y + Directions.VECTORS[i].getDy();
                if (newX == Maps.MAP_TARGETS[mapId].getX() && newY == Maps.MAP_TARGETS[mapId].getY()) {
                    paths.add(new Coordinate(newX, newY));
                    return;
                }
                if (newX < 0 || newX >= GamePlays.HEIGHT || newY < 0 || newY >= GamePlays.WIDTH)
                    continue;
                if (Maps.MAP_SPRITES[mapId][newX][newY] == Maps.GRASS || Maps.MAP_SPRITES[mapId][newX][newY] == Maps.OBSTACLE)
                    continue;
                findPath(newX, newY, i);
                break;
            }
    }

    public void init(int mapId) {
        if (mapId < 0 || mapId >= Maps.MAP_SPRITES.length)
            return;
        this.mapId = mapId;
        this.group = new Group();
        this.scene = new Scene(group);
        findPath(Maps.MAP_SPAWNS[mapId].getX(), Maps.MAP_SPAWNS[mapId].getY(), -1);
        addTower(new NormalTower(6 * GamePlays.SPRITE_SIZE, 19 * GamePlays.SPRITE_SIZE));

        /* Drag and drop sample */
        ImageView sourceFld = new ImageView(AssetUtil.getTowerImage("205"));
        sourceFld.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        // Add mouse event handlers for the source
        sourceFld.setOnMousePressed(event -> {
            System.out.println("Event on Source: mouse pressed");
            event.setDragDetect(true);
        });
        sourceFld.setOnMouseReleased(event -> {
            System.out.println("Event on Source: mouse released");
        });
        sourceFld.setOnMouseDragged(event -> {
            System.out.println("Event on Source: mouse dragged");
            event.setDragDetect(false);
        });
        sourceFld.setOnDragDetected(event -> {
            sourceFld.startFullDrag();
            System.out.println("Event on Source: drag detected");
        });
        group.getChildren().addAll(sourceFld);
        /* Drag and drop sample */
        AnimationTimer animationTimer = new AnimationTimer() {
            long lastAddEnemies = 0;

            @Override
            public void handle(long timestamp) {
                latestTimestamp = timestamp;
                render();
                update();
                destroy();
                if (timestamp - lastAddEnemies > Times.SECOND_TO_NANO) {
                    lastAddEnemies = timestamp;
                    addEnemy(new NormalEnemy());
                }
            }
        };
        animationTimer.start();
    }

    private void addEnemy(BaseEnemy enemy) {
        enemy.setX(Maps.MAP_SPAWNS[mapId].getX() * GamePlays.SPRITE_SIZE);
        enemy.setY(Maps.MAP_SPAWNS[mapId].getY() * GamePlays.SPRITE_SIZE);
        enemies.add(enemy);
    }

    private void addTower(BaseTower tower) {
        Coordinate coordinate = new Coordinate(tower.getX() / GamePlays.SPRITE_SIZE, tower.getY() / GamePlays.SPRITE_SIZE);
        if (coordinate.getX() < 0 || coordinate.getX() + 1 >= GamePlays.HEIGHT || coordinate.getY() < 0 || coordinate.getY() + 1 >= GamePlays.WIDTH)
            return;
        for (int i = 0; i <= 1; i++)
            for (int j = 0; j <= 1; j++)
                if (Maps.MAP_SPRITES[mapId][coordinate.getX() + i][coordinate.getY() + i] != Maps.GRASS)
                    return;
        for (BaseTower tempTower : towers)
            if (Math.abs(tempTower.getX() - tower.getX()) <= GamePlays.SPRITE_SIZE && Math.abs(tempTower.getY() - tower.getY()) <= GamePlays.SPRITE_SIZE)
                return;
        towers.add(tower);
    }

    private void render() {
        while (group.getChildren().size() > 1)
            group.getChildren().remove(1);
        group.getChildren().add(new ImageView(AssetUtil.getMapImage(mapId)));
        for (BaseEnemy enemy : enemies)
            enemy.render(group);
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets)
                bullet.render(group);
            tower.render(group);
        }
    }

    private void update() {
        for (BaseEnemy enemy : enemies)
            enemy.update(paths);
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets)
                bullet.update();
            tower.update(enemies, latestTimestamp);
        }
    }

    private void destroy() {
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getX() == bullets.get(i).getTargetEnemy().getX()
                        && bullets.get(i).getY() == bullets.get(i).getTargetEnemy().getY()) {
                    bullets.get(i).getTargetEnemy().setHp(bullets.get(i).getTargetEnemy().getHp() - bullets.get(i).getDamage());
                    bullets.remove(i--);
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getX() == paths.get(paths.size() - 1).getX() * GamePlays.SPRITE_SIZE
                    && enemies.get(i).getY() == paths.get(paths.size() - 1).getY() * GamePlays.SPRITE_SIZE)
                enemies.remove(i--);
            else if (enemies.get(i).getHp() <= 0)
                enemies.remove(i--);
    }
}
