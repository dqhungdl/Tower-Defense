package com.uet.towerdefense.common.util;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import javafx.scene.image.Image;

import java.net.URL;

public class AssetUtil {

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
}
