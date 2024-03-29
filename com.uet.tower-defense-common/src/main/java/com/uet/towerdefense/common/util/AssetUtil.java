package com.uet.towerdefense.common.util;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AssetUtil {
    public static Image getMenuImage() {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Menu/menu.png").toString();
        return new Image(fileName, GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE + GamePlays.ADDED_HEIGHT, false, false);
    }

    public static Image getImage(String imageId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png").toString();
        return new Image(fileName, GamePlays.SPRITE_SIZE, GamePlays.SPRITE_SIZE, false, false);
    }

    public static Image getMapImage(int mapId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Maps/map" + mapId + ".png").toString();
        return new Image(fileName, GamePlays.WIDTH * GamePlays.SPRITE_SIZE, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE, false, false);
    }

    public static Image getTowerImage(String imageId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png").toString();
        return new Image(fileName, GamePlays.TOWER_SIZE, GamePlays.TOWER_SIZE, false, false);
    }

    public static Image getEnemyImage(String imageId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png").toString();
        return new Image(fileName, GamePlays.ENEMY_SIZE, GamePlays.ENEMY_SIZE, false, false);
    }

    public static Image getBulletImage(String imageId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png").toString();
        return new Image(fileName, GamePlays.BULLET_SIZE, GamePlays.BULLET_SIZE, false, false);
    }

    public static Image getBackgroundImage(String imageId) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Menu/background" + imageId + ".png").toString();
        return new Image(fileName, GamePlays.ADDED_WIDTH, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE, false, false);
    }

    public static Image getButtonImage(String imageId, int width, int height) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Menu/button" + imageId + ".png").toString();
        return new Image(fileName, width, height, false, false);
    }

    public static MediaPlayer getBackgroundSound() {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Sound/background.mp3").getPath();
        Media media = new Media(new File(fileName).toURI().toString());
        return new MediaPlayer(media);
    }

    public static AudioClip getGameSound(String soundName) {
        String fileName = AssetUtil.class.getClassLoader().getResource("AssetsKit_2/Sound/" + soundName + ".wav").toExternalForm();
        return new AudioClip(fileName);
    }
}
