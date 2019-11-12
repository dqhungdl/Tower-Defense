package com.uet.towerdefense.common.util;

import com.uet.towerdefense.common.enums.graphics.GamePlays;
import javafx.scene.image.Image;

public class AssetUtil {

    public static Image getMapImage(int mapId) {
        return new Image("file:src/main/resources/AssetsKit_2/Maps/map" + mapId + ".png", GamePlays.WIDTH * GamePlays.SPRITE_SIZE, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE, false, false);
    }

    public static Image getTowerImage(String imageId) {
        return new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png", GamePlays.TOWER_SIZE, GamePlays.TOWER_SIZE, false, false);
    }

    public static Image getEnemyImage(String imageId) {
        return new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile" + imageId + ".png", GamePlays.ENEMY_SIZE, GamePlays.ENEMY_SIZE, false, false);
    }
}
