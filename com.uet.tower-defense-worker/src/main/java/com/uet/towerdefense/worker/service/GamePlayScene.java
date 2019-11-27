package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.GameStage;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.PlaneEnemy;
import com.uet.towerdefense.common.pojo.enemies.SmallEnemy;
import com.uet.towerdefense.common.pojo.enemies.TankEnemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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

    @Autowired
    private NotificationService notificationService;

    private Scene scene;
    private Group group;
    private int mapId;
    private GameStage gameStage;

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
        this.gameStage = new GameStage(GamePlays.BASE_HP, GamePlays.BASE_MONEY, 0);
        nodeService.setNodes(group.getChildren());
        mapService.init(mapId);
        menuService.init(gameStage);
        notificationService.init();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(4), new EventHandler<ActionEvent>() {

            private long timestamp;
            private long nextTimestamp = new Date().getTime() + GamePlays.SECOND_START_GAME;
            private LinkedList<BaseEnemy> enemies = new LinkedList<>();

            void enemiesGeneration(int stage) {
                int maxLevelSmallEnemy = Math.min(stage / (GamePlays.BASE_STAGE / 4), 3);
                int maxLevelTankEnemy = Math.min(stage / (GamePlays.BASE_STAGE / 2), 2);
                int maxLevelPlaneEnemy = Math.min(stage / (GamePlays.BASE_STAGE / 2), 2);
                int smallEnemies = 10 + stage * 2, tankEnemies = 5 + stage, planeEnemies = 3 + stage;
                Random random = new Random();
                while (smallEnemies > 0) {
                    smallEnemies--;
                    enemies.add(new SmallEnemy(Math.abs(random.nextInt()) % (maxLevelSmallEnemy + 1)));
                }
                while (tankEnemies > 0) {
                    tankEnemies--;
                    enemies.add(new TankEnemy(Math.abs(random.nextInt()) % (maxLevelTankEnemy + 1)));
                }
                while (planeEnemies > 0) {
                    planeEnemies--;
                    enemies.add(new PlaneEnemy(Math.abs(random.nextInt()) % (maxLevelPlaneEnemy + 1)));
                }
            }

            private void update() {
                mapService.render();
                mapService.update(timestamp);
                menuService.render(Math.max(nextTimestamp - timestamp, 0) / GamePlays.SECOND_TO_MILLI);
            }

            @Override
            public void handle(ActionEvent actionEvent) {
                timestamp = new Date().getTime();
                boolean isExistedEnemy = false;
                for (Node node : group.getChildren())
                    if (node.getId().equals(RenderLevels.ENEMY)) {
                        isExistedEnemy = true;
                        break;
                    }
                if (!isExistedEnemy && enemies.size() == 0) {
                    enemiesGeneration(gameStage.getStage());
                    gameStage.setStage(gameStage.getStage() + 1);
                    nextTimestamp = timestamp + GamePlays.SECOND_BETWEEN_STAGES;
                }
                update();
                if (enemies.size() > 0 && timestamp >= nextTimestamp) {
                    nextTimestamp = timestamp + GamePlays.SECOND_TO_MILLI / 2;
                    mapService.addEnemy(enemies.pollFirst());
                }
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
