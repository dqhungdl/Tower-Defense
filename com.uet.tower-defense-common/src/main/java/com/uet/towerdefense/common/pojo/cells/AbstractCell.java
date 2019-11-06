package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;

public abstract class AbstractCell extends AbstractStaticEntity<Long> implements BaseCell<Long> {

    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract String getCellType();
}
