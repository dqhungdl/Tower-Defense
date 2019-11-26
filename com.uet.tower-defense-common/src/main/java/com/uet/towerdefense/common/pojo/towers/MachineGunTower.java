package com.uet.towerdefense.common.pojo.towers;

import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.pojo.bullets.MachineGunBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import javafx.scene.Node;

import java.util.List;

public class MachineGunTower extends AbstractTower {

    private static final String STAND_IMAGE_ID = "182";
    private static final String[] TOWER_IMAGE_ID = new String[]{"203", "203"};

    public MachineGunTower(double x, double y, List<Node> nodes) {
        super(x,y,nodes);
        speed = Towers.MACHINE_GUN_SPEED[level];
        range = Towers.MACHINE_GUN_RANGE[level];
        damage = Towers.MACHINE_GUN_DAMAGE[level];
        money = Towers.MACHINE_GUN_MONEY[level];
    }

    @Override
    public String getTowerType() {
        return Towers.MACHINE_GUN;
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
        bullets.add(new MachineGunBullet(x, y, direction, damage, targetEnemy));
    }
}
