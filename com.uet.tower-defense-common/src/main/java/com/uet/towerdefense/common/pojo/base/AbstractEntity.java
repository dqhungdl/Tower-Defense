package com.uet.towerdefense.common.pojo.base;

public abstract class AbstractEntity<Tid extends Object> implements BaseEntity<Tid> {

    protected Tid id;

    protected int x;

    protected int y;

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
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
