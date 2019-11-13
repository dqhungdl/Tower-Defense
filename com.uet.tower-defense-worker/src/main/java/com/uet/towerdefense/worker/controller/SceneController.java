package com.uet.towerdefense.worker.controller;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import com.uet.towerdefense.worker.service.GamePlayScene;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SceneController {

    @Autowired
    private GamePlayScene gamePlayScene;

    private Stage stage;

    public void setScene(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(GamePlays.TITLE);
        this.stage.setWidth(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 300);
        this.stage.setHeight(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        this.stage.setResizable(false);
        stage.show();
    }

    public void toGamePlayScene() {
        gamePlayScene.init(0);
        stage.setScene(gamePlayScene.getScene());
    }
}
