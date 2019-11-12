package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.base.BaseEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.NormalEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.Bullet;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private List<BaseEntity> entities = new ArrayList<>();

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
        this.group = new Group();
        this.group.getChildren().add(canvas);
        this.scene = new Scene(group);
        this.group.getChildren().add(new ImageView(AssetUtil.getMapImage(mapId)));
    }

    // Tower utils
    public void addTower(BaseTower tower) {
        entities.add(tower);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        animationTimer.start();
    }

    // Enemy utils
    public void addEnemy(BaseEnemy enemy) {
        entities.add(enemy);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        animationTimer.start();
    }

    public  void addBullet(Bullet bullet)
    {
        entities.add(bullet);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        animationTimer.start();
    }

    private void render() {
        this.group.getChildren().clear();
        this.group.getChildren().add(new ImageView(AssetUtil.getMapImage(mapId)));
        for (BaseEntity entity : entities)
            entity.render(group);
    }

    private void update() {
        for (BaseEntity entity : entities)
            entity.update();
    }
}
