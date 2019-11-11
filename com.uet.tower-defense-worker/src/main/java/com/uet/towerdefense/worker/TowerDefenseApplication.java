package com.uet.towerdefense.worker;

import com.uet.towerdefense.worker.controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TowerDefenseApplication extends Application {

    @Autowired
    private SceneController sceneController;

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        Application.launch(TowerDefenseApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(TowerDefenseApplication.class);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneController.setScene(primaryStage);
        sceneController.toGamePlayScene();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.stop();
    }
}
