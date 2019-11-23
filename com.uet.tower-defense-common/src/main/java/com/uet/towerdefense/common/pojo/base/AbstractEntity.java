package com.uet.towerdefense.common.pojo.base;

public abstract class AbstractEntity<Tid extends Object> implements BaseEntity<Tid> {

    protected Tid id;

    protected double x;

    protected double y;

    protected String entityType;

    @Override
    public Tid getId() {
        return id;
    }

    @Override
    public void setId(Tid id) {
        this.id = id;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
}
