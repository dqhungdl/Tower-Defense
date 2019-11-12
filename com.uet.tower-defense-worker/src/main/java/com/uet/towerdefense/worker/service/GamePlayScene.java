package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamePlayScene {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Group group;
    private int mapId;
    private List<BaseTower> towers = new ArrayList<>();
    private List<BaseEnemy> enemies = new ArrayList<>();

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    // Init canvas and draw a map
    public void init(int mapId) {
        if (mapId < 0 || mapId >= Maps.MAP_SPRITES.length)
            return;
        this.mapId = mapId;
        this.canvas = new Canvas(GamePlays.WIDTH * GamePlays.SPRITE_SIZE, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.group = new Group();
        this.group.getChildren().add(canvas);
        this.scene = new Scene(group);
        // Draw a map
        graphicsContext.drawImage(AssetUtil.getMapImage(mapId), 0, 0);
    }

    // Tower utils
    public void addTower(BaseTower tower) {
        if (tower.getX() + 1 >= GamePlays.HEIGHT || tower.getY() + 1 >= GamePlays.WIDTH)
            return;
        for (int i = 0; i <= 1; i++)
            for (int j = 0; j <= 1; j++)
                if (Maps.MAP_SPRITES[mapId][tower.getX() + i][tower.getY() + j] != Maps.GRASS)
                    return;
        graphicsContext.drawImage(AssetUtil.getTowerImage(tower.getStandImageId()), tower.getY(), tower.getX());
        graphicsContext.drawImage(AssetUtil.getTowerImage(tower.getTowerImageId()), tower.getY(), tower.getX() - 10);
        towers.add(tower);
    }

    public void towerLevelUp(int x, int y) {
        for (BaseTower tower : towers)
            if (tower.getX() == x && tower.getY() == y) {
                tower.levelUp();
                graphicsContext.drawImage(AssetUtil.getTowerImage(tower.getStandImageId()), tower.getY() * GamePlays.SPRITE_SIZE, tower.getX() * GamePlays.SPRITE_SIZE);
                graphicsContext.drawImage(AssetUtil.getTowerImage(tower.getTowerImageId()), tower.getY() * GamePlays.SPRITE_SIZE, tower.getX() * GamePlays.SPRITE_SIZE - 10);
                break;
            }
    }

    // Enemy utils
    public void addEnemy(BaseEnemy enemy) {
        enemy.render(graphicsContext);
        enemies.add(enemy);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                enemy.render(graphicsContext);
                enemy.update();
            }
        };
        animationTimer.start();
    }
}
