package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.MachineGunTower;
import com.uet.towerdefense.common.pojo.towers.RocketTower;
import com.uet.towerdefense.common.pojo.towers.SniperTower;
import com.uet.towerdefense.common.util.AssetUtil;
import com.uet.towerdefense.worker.controller.SceneController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuScene {
    @Autowired
    private SceneController sceneController;

    private Scene scene;
    private Group group;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Group getGroup() {
        return group;
    }

    public void menuInit()
    {
        this.group = new Group();
        this.scene = new Scene(group);
        ImageView imageView = new ImageView(AssetUtil.getMenuSence());
        imageView.setId(RenderLevels.MENU_SCENE);
        group.getChildren().add(imageView);


        imageView.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            if (x>475 && x<725 && y>260 && y<335)
                sceneController.toGamePlayScene();
            else if (x>475 && x<725 && y>384 && y<460)
                System.out.println("Resume");
            else if (x>475 && x<725 && y>510 && y<586)
                System.exit(0);
        });

    }





}
