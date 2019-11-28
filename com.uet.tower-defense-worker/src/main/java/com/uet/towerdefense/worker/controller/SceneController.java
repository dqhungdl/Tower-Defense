package com.uet.towerdefense.worker.controller;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import com.uet.towerdefense.worker.scene.GamePlayScene;
import com.uet.towerdefense.worker.scene.MenuScene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SceneController {

    @Autowired
    private GamePlayScene gamePlayScene;

    @Autowired
    private MenuScene menuScene;

    private Stage stage;

    public void setScene(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(GamePlays.TITLE);
        this.stage.setWidth(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH);
        this.stage.setHeight(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE + GamePlays.ADDED_HEIGHT);
        this.stage.setResizable(false);
        MediaPlayer mediaPlayer = AssetUtil.getBackgroundSound();
        mediaPlayer.setAutoPlay(true);
        stage.show();
    }

    public void toGamePlayScene() {
        gamePlayScene.init(1);
        stage.setScene(gamePlayScene.getScene());
    }


    public void toMenuScene() {
        menuScene.init();
        stage.setScene(menuScene.getScene());
    }
}
