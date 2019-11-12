package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class NormalEnemy extends AbstractEnemy {

    private static final String ENEMY_IMAGE_ID = "245";

    public NormalEnemy(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        hp = 1;
        speed = 1;
        defense = 1;
        gold = 1;
    }

    @Override
    public String getEnemyType() {
        return Enemies.NORMAL;
    }

    @Override
    public String getEnemyImageId() {
        return ENEMY_IMAGE_ID;
    }

    @Override
    public void render(Group group) {
        ImageView imageView = new ImageView(AssetUtil.getEnemyImage(ENEMY_IMAGE_ID));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setRotate(this.direction);
        group.getChildren().addAll(imageView);
    }

    @Override
    public void update() {
        y++;
    }
}
