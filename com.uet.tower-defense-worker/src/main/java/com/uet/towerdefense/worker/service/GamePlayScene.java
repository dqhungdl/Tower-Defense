package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.Pair;
import com.uet.towerdefense.common.enums.graphics.Directions;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.base.BaseEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.NormalEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Canvas canvas;
    private Group group;
    private int mapId;
    private List<BaseEnemy> enemies = new ArrayList<>();
    private List<Pair> paths = new ArrayList<>();

    private BooleanProperty booleanProperty = new SimpleBooleanProperty(true);

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void findPath(int x, int y, int lastDirection) {
        paths.add(new Pair(x, y));
        for (int i = 0; i < 4; i++)
            if ((i + 2) % 4 != lastDirection) {
                int newX = x + Directions.VECTORS[i].getDx();
                int newY = y + Directions.VECTORS[i].getDy();
                if (newX == Maps.MAP_TARGETS[mapId].getX() && newY == Maps.MAP_TARGETS[mapId].getY()) {
                    paths.add(new Pair(newX, newY));
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

    // Init canvas and draw a map
    public void init(int mapId) {
        if (mapId < 0 || mapId >= Maps.MAP_SPRITES.length)
            return;
        this.mapId = mapId;
        this.canvas = new Canvas(GamePlays.WIDTH * GamePlays.SPRITE_SIZE, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        this.group = new Group();
        this.group.getChildren().add(canvas);
        this.scene = new Scene(group);
        this.group.getChildren().add(new ImageView(AssetUtil.getMapImage(mapId)));
        findPath(Maps.MAP_SPAWNS[mapId].getX(), Maps.MAP_SPAWNS[mapId].getY(), -1);
        AnimationTimer animationTimer = new AnimationTimer() {

            long lastAddEnemies = 0;

            @Override
            public void handle(long timestamp) {
                render();
                update();
                if (timestamp - lastAddEnemies > 1000000000) {
                    lastAddEnemies = timestamp;
                    addEnemy(new NormalEnemy());
                }
            }
        };
        animationTimer.start();
    }

    // Tower utils
    private void addTower(BaseTower tower) {

    }

    // Enemy utils
    private void addEnemy(BaseEnemy enemy) {
        enemy.setX(Maps.MAP_SPAWNS[mapId].getX() * GamePlays.SPRITE_SIZE);
        enemy.setY(Maps.MAP_SPAWNS[mapId].getY() * GamePlays.SPRITE_SIZE);
        enemies.add(enemy);
    }

    private void render() {
        this.group.getChildren().clear();
        this.group.getChildren().add(new ImageView(AssetUtil.getMapImage(mapId)));
        for (BaseEnemy enemy : enemies)
            enemy.render(group);
    }

    private void update() {
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).update(paths);
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getX() == paths.get(paths.size() - 1).getX() * GamePlays.SPRITE_SIZE
                    && enemies.get(i).getY() == paths.get(paths.size() - 1).getY() * GamePlays.SPRITE_SIZE)
                enemies.remove(i--);
    }
}
