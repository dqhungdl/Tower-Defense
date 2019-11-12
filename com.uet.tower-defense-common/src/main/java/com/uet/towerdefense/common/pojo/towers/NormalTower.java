package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class NormalTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "180";
    private static final String[] TOWER_IMAGE_ID = new String[]{"249", "250"};

    public NormalTower(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = Towers.NORMAL_SPEED[level];
        range = Towers.NORMAL_RANGE[level];
        damage = Towers.NORMAL_DAMAGE[level];
        gold = Towers.NORMAL_GOLD[level];
    }

    @Override
    public String getTowerType() {
        return Towers.NORMAL;
    }

    @Override
    public String getStandImageId() {
        return STAND_IMAGE_ID;
    }

    @Override
    public String getTowerImageId() {
        return TOWER_IMAGE_ID[level];
    }

    @Override
    public void render(Group group) {
        ImageView imageViewTower = new ImageView(AssetUtil.getTowerImage(TOWER_IMAGE_ID[level]));
        ImageView imageViewStand = new ImageView(AssetUtil.getTowerImage(STAND_IMAGE_ID));
        imageViewTower.setX(x);
        imageViewTower.setY(y);
        imageViewStand.setX(x);
        imageViewStand.setY(y);
        imageViewTower.setRotate(this.direction);
        group.getChildren().addAll(imageViewStand);
        group.getChildren().addAll(imageViewTower);
    }

    @Override
    public void update() {
        /*if (direction<360)
        {
            direction++;
        }
        else direction = 0;*/
    }
}
