package com.uet.towerdefense.common.pojo.base;

import com.uet.towerdefense.common.pojo.enums.Entities;

public abstract class AbstractEntity<Tid extends Object> implements BaseEntity<Tid> {

    private Tid id;

    private Entities entityType;

    @Override
    public Tid getId() {
        return id;
    }

    @Override
    public void setId(Tid id) {
        this.id = id;
    }

    @Override
    public abstract String getEntityType();
}
