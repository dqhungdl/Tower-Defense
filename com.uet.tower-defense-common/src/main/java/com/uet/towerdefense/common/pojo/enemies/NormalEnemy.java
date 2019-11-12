package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.data.Pair;
import com.uet.towerdefense.common.data.Vector;
import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class NormalEnemy extends AbstractEnemy {

    private static final String ENEMY_IMAGE_ID = "270";

    public NormalEnemy() {
        hp = Enemies.NORMAL_HP;
        speed = Enemies.NORMAL_SPEED;
        defense = Enemies.NORMAL_DEFENSE;
        gold = Enemies.NORMAL_GOLD;
    }

    @Override
    public String getEnemyType() {
        return Enemies.NORMAL;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID;
    }
}
