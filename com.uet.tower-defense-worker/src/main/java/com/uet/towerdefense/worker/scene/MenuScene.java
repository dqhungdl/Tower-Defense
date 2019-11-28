package com.uet.towerdefense.worker.scene;

import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import com.uet.towerdefense.worker.controller.SceneController;
import com.uet.towerdefense.worker.service.ResumeService;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuScene {

    @Autowired
    private SceneController sceneController;

    @Autowired
    private ResumeService resumeService;

    private Scene scene;
    private Group group;

    public Scene getScene() {
        return scene;
    }

    public void init() {
        this.group = new Group();
        this.scene = new Scene(group);
        // Add menu
        ImageView menuImage = new ImageView(AssetUtil.getMenuImage());
        group.getChildren().add(menuImage);
        // Add new game button
        ImageView newGameButton = new ImageView(AssetUtil.getButtonImage("9", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        newGameButton.setX(475);
        newGameButton.setY(260);
        group.getChildren().add(newGameButton);
        newGameButton.setOnMouseEntered(mouseEvent -> {
            newGameButton.setOpacity(Animations.DARK_OPACITY);
        });
        newGameButton.setOnMouseExited(mouseEvent -> {
            newGameButton.setOpacity(Animations.NORMAL_OPACITY);
        });
        newGameButton.setOnMouseClicked(mouseEvent -> {
            sceneController.toGamePlayScene();
        });
        // Add resume button
        ImageView resumeButton = new ImageView(AssetUtil.getButtonImage("10", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        resumeButton.setX(475);
        resumeButton.setY(375);
        group.getChildren().add(resumeButton);
        resumeButton.setOnMouseEntered(mouseEvent -> {
            resumeButton.setOpacity(Animations.DARK_OPACITY);
        });
        resumeButton.setOnMouseExited(mouseEvent -> {
            resumeButton.setOpacity(Animations.NORMAL_OPACITY);
        });
        resumeButton.setOnMouseClicked(mouseEvent -> {
            sceneController.toGamePlayScene();
            resumeService.load();
        });
        ImageView exitButton = new ImageView(AssetUtil.getButtonImage("8", GamePlays.BUTTON_WIDTH, GamePlays.BUTTON_HEIGHT));
        exitButton.setX(475);
        exitButton.setY(490);
        group.getChildren().add(exitButton);
        exitButton.setOnMouseEntered(mouseEvent -> {
            exitButton.setOpacity(Animations.DARK_OPACITY);
        });
        exitButton.setOnMouseExited(mouseEvent -> {
            exitButton.setOpacity(Animations.NORMAL_OPACITY);
        });
        exitButton.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
    }
}
