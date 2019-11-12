package com.uet.towerdefense.common.pojo.towers;
import com.uet.towerdefense.common.pojo.base.AbstractDynamicEntity;

public abstract class AbstractBullet extends  AbstractDynamicEntity<Long> implements BaseBullet<Long>{
    protected double speed;

    protected  double direction;

    protected int damage;

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
