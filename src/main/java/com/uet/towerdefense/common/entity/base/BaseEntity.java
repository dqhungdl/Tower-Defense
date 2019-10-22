package com.uet.towerdefense.common.entity.base;

import java.io.Serializable;

public interface BaseEntity<Tid extends Object> extends Serializable {

    Tid getId();

    void setId(Tid id);
}
