package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.RocketBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import javafx.scene.Node;

import java.util.List;

public class RocketTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "181";
    private static final String[] TOWER_IMAGE_ID = new String[]{"309", "310", "205"};

    public RocketTower(double x, double y, List<Node> nodes) {
        super(x, y, nodes);
        speed = Towers.ROCKET_SPEED[level];
        range = Towers.ROCKET_RANGE[level];
        damage = Towers.ROCKET_DAMAGE[level];
        money = Towers.ROCKET_MONEY[level];
    }

    @Override
    public String getTowerType() {
        return Towers.ROCKET;
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
    public void addBullet(BaseEnemy targetEnemy) {
        bullets.add(new RocketBullet(x, y, direction, damage, level, targetEnemy));
    }
}
