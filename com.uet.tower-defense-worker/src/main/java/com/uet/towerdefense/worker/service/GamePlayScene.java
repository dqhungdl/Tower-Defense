package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.enemies.NormalEnemy;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GamePlayScene {

    @Autowired
    private MapService mapService;

    @Autowired
    private MenuService menuService;

    private Scene scene;
    private Group group;
    private int mapId;
    private long latestTimestamp;
    private List<NodeCompare> nodes;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void init(int mapId) {
        if (mapId < 0 || mapId >= Maps.MAP_SPRITES.length)
            return;
        nodes = new ArrayList<>();
        this.mapId = mapId;
        this.group = new Group();
        this.scene = new Scene(group);
        mapService.init(nodes, mapId);
        menuService.init(group, nodes);
        AnimationTimer animationTimer = new AnimationTimer() {
            long lastAddEnemies = 0;

            @Override
            public void handle(long timestamp) {
                latestTimestamp = timestamp;
                update();
                if (timestamp - lastAddEnemies > GamePlays.SECOND_TO_NANO) {
                    lastAddEnemies = timestamp;
                    mapService.addEnemy(new NormalEnemy());
                }
            }
        };
        animationTimer.start();
    }

    private void update() {
        for (int i = 0; i < group.getChildren().size(); i++) {
            if (group.getChildren().get(i).getId().equals(RenderLevels.MAP)
                    || group.getChildren().get(i).getId().equals(RenderLevels.DRAG_DROP)
                    || group.getChildren().get(i).getId().equals(RenderLevels.TEMP_DRAG_DROP))
                continue;
            group.getChildren().remove(i--);
        }
        Collections.sort(nodes);
        int p = 0;
        for (NodeCompare node : nodes) {
            while (p < group.getChildren().size() && node.getNode().getId().compareTo(group.getChildren().get(p).getId()) >= 0)
                p++;
            group.getChildren().add(p, node.getNode());
        }
        nodes.clear();
        mapService.render();
        mapService.update(latestTimestamp);
        menuService.render();
    }
}
