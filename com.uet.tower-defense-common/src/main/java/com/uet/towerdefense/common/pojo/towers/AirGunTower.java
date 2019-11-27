package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.AirGunBullet;
import com.uet.towerdefense.common.pojo.bullets.MachineGunBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.SmallEnemy;
import javafx.scene.Node;

import java.util.List;

public class AirGunTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "182";
    private static final String[] TOWER_IMAGE_ID = new String[]{"307", "308", "206"};

    public AirGunTower(double x, double y, List<Node> nodes) {
        super(x, y, nodes);
        speed = Towers.AIR_GUN_SPEED[level];
        range = Towers.AIR_GUN_RANGE[level];
        damage = Towers.AIR_GUN_DAMAGE[level];
        money = Towers.AIR_GUN_MONEY[level];
    }

    @Override
    public String getTowerType() {
        return Towers.AIR_GUN;
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
        bullets.add(new AirGunBullet(x, y, direction, damage, level, targetEnemy));
    }
}
