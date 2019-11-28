package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.SniperBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import javafx.scene.Node;

import java.util.List;

public class SniperTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "180";
    private static final String[] TOWER_IMAGE_ID = new String[]{"249", "302", "250"};

    public SniperTower(double x, double y, List<Node> nodes) {
        super(x, y, nodes);
        speed = Towers.SNIPER_SPEED[level];
        range = Towers.SNIPER_RANGE[level];
        damage = Towers.SNIPER_DAMAGE[level];
        money = Towers.SNIPER_MONEY[level];
    }

    @Override
    public void levelUp() {
        super.levelUp();
        speed = Towers.SNIPER_SPEED[level];
        range = Towers.SNIPER_RANGE[level];
        damage = Towers.SNIPER_DAMAGE[level];
        money = Towers.SNIPER_MONEY[level];
    }

    @Override
    public String getTowerType() {
        return Towers.SNIPER;
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
        bullets.add(new SniperBullet(x, y, direction, damage, level, targetEnemy));
    }
}
