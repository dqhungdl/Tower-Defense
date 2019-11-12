package com.uet.towerdefense.common.pojo.enemies;

import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class NormalEnemy extends AbstractEnemy {

    private static final String ENEMY_IMAGE_ID = "245";

    public NormalEnemy(int x, int y, double direction) {
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
    public void render(GraphicsContext graphicsContext) {
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        ImageView imageView = new ImageView(AssetUtil.getEnemyImage(ENEMY_IMAGE_ID));
        imageView.setRotate(this.direction);
        Image image = imageView.snapshot(snapshotParameters, null);
        graphicsContext.drawImage(image, x, y);
    }

    @Override
    public void update() {
        y += speed;
    }
}
