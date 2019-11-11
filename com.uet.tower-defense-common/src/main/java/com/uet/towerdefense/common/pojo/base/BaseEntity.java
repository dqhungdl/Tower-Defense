package com.uet.towerdefense.common.pojo.base;

import java.io.Serializable;

public interface BaseEntity<Tid extends Object> extends Serializable {

    Tid getId();

    void setId(Tid id);

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    String getEntityType();
}
