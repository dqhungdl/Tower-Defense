package com.uet.towerdefense.worker.controller;

import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.base.BaseEntity;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.NormalEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.Bullet;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.worker.service.GamePlayScene;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SceneController {

    @Autowired
    private GamePlayScene gamePlayScene;

    private Stage stage;

    private Animation animation;

    public void setScene(Stage stage) {
        this.stage = stage;
        this.animation = new Timeline();
        this.stage.setTitle(GamePlays.TITLE);
        this.stage.setWidth(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        this.stage.setHeight(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        this.stage.setResizable(false);
        stage.show();
    }

    public void toGamePlayScene() {
        animation.stop();
        gamePlayScene.init(0);
        BaseEnemy normalEnemy = new NormalEnemy(0, 0, 0);
        gamePlayScene.addEnemy(normalEnemy);

        Bullet bullet = new Bullet(0,0, 315);
        gamePlayScene.addBullet(bullet);

        NormalTower normalTower = new NormalTower(0,0, 135);
        gamePlayScene.addTower(normalTower);




        stage.setScene(gamePlayScene.getScene());
    }
}
