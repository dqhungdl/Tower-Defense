package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.GameStage;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.SmallEnemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamePlayScene {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MapService mapService;

    @Autowired
    private MenuService menuService;

    private Scene scene;
    private Group group;
    private int mapId;
    private long latestTimestamp;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void init(int mapId) {
        if (mapId < 0 || mapId >= Maps.MAP_SPRITES.length)
            return;
        this.mapId = mapId;
        this.group = new Group();
        this.scene = new Scene(group);
        nodeService.setNodes(group.getChildren());
        mapService.init(mapId);
        menuService.init(new GameStage(100, 20));
        KeyFrame keyFrame = new KeyFrame(Duration.millis(4), new EventHandler<ActionEvent>() {

            private int stage = 0;
            private long nextTimestamp = new Date().getTime() + GamePlays.SECOND_START_GAME;
            private LinkedList<BaseEnemy> enemies = new LinkedList<>();

            void enemiesGeneration(int stage) {
                int level = Math.min(stage / 10, 3);
                int smallEnemies = 10 + level * 2, tankEnemies = 0, planeEnemies = 0;
                Random random = new Random();
                while (smallEnemies > 0) {
                    smallEnemies--;
                    enemies.add(new SmallEnemy(Math.abs(random.nextInt()) % (level + 1)));
                }
            }

            @Override
            public void handle(ActionEvent actionEvent) {
                long timestamp = new Date().getTime();
                if (enemies.size() == 0) {
                    enemiesGeneration(stage++);
                    nextTimestamp = timestamp + GamePlays.SECOND_BETWEEN_STAGES;
                }
                latestTimestamp = timestamp;
                update();
                if (enemies.size() > 0 && timestamp >= nextTimestamp) {
                    nextTimestamp = timestamp + GamePlays.SECOND_TO_MILLI;
                    mapService.addEnemy(enemies.pollFirst());
                }
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void update() {
        mapService.render();
        mapService.update(latestTimestamp);
        menuService.render();
    }
}
