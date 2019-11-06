package com.uet.towerdefense.common.pojo.base;

import java.io.Serializable;

public interface BaseEntity<Tid extends Object> extends Serializable {

    Tid getId();

    void setId(Tid id);

    String getEntityType();
}
