package com.uet.towerdefense.common.pojo.base;

public abstract class AbstractEntity<Tid extends Object> implements BaseEntity<Tid> {

    private Tid id;

    private String entityType;

    @Override
    public Tid getId() {
        return id;
    }

    @Override
    public void setId(Tid id) {
        this.id = id;
    }
}
