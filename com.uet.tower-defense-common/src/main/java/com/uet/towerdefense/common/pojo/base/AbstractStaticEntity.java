package com.uet.towerdefense.common.pojo.base;

import com.uet.towerdefense.common.enums.Entities;

public abstract class AbstractStaticEntity<Tid extends Object> extends AbstractEntity<Tid> implements BaseStaticEntity<Tid> {

    @Override
    public String getEntityType() {
        return Entities.STATIC;
    }
}
