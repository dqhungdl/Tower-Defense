package com.uet.towerdefense.worker.controller;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.worker.service.GamePlayScene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SceneController {

    @Autowired
    private GamePlayScene gamePlayComponent;

    private Stage stage;

    public void setScene(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(GamePlays.TITLE);
        this.stage.setWidth(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH);
        this.stage.setHeight(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        this.stage.setResizable(false);
        stage.show();
    }

    public void toGamePlayScene() {
        gamePlayComponent.init(0);
        stage.setScene(gamePlayComponent.getScene());
    }
}
