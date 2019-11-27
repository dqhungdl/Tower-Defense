package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.Animations;
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
        ImageView menuView = new ImageView(AssetUtil.getMenuSence());
        group.getChildren().add(menuView);

        ImageView newGame = new ImageView(AssetUtil.getButtonImage("9", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        newGame.setX(475);
        newGame.setY(260);
        group.getChildren().add(newGame);
        newGame.setOnMouseEntered(mouseEvent -> {
            newGame.setOpacity(Animations.DARK_OPACITY);
        });

        newGame.setOnMouseExited(mouseEvent -> {
            newGame.setOpacity(Animations.NORMAL_OPACITY);
        });

        newGame.setOnMouseClicked(mouseEvent ->{
            sceneController.toGamePlayScene();
        } );


        ImageView resume = new ImageView(AssetUtil.getButtonImage("10", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        resume.setX(475);
        resume.setY(375);
        group.getChildren().add(resume);
        resume.setOnMouseEntered(mouseEvent -> {
            resume.setOpacity(Animations.DARK_OPACITY);
        });

        resume.setOnMouseExited(mouseEvent -> {
            resume.setOpacity(Animations.NORMAL_OPACITY);
        });

        resume.setOnMouseClicked(mouseEvent ->{
            sceneController.toGamePlayScene();
        } );

        ImageView exit = new ImageView(AssetUtil.getButtonImage("8", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        exit.setX(475);
        exit.setY(490);
        group.getChildren().add(exit);
        exit.setOnMouseEntered(mouseEvent -> {
            exit.setOpacity(Animations.DARK_OPACITY);
        });

        exit.setOnMouseExited(mouseEvent -> {
            exit.setOpacity(Animations.NORMAL_OPACITY);
        });

        exit.setOnMouseClicked(mouseEvent ->{
            System.exit(0);
        } );

    }





}
