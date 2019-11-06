package com.uet.towerdefense.common.pojo.base;

import com.uet.towerdefense.common.enums.Entities;

public abstract class AbstractDynamicEntity<Tid extends Object> extends AbstractEntity<Tid> implements BaseDynamicEntity<Tid> {

    @Override
    public String getEntityType() {
        return Entities.DYNAMIC;
    }
}
