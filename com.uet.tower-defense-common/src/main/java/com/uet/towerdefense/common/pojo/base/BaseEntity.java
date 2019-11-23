package com.uet.towerdefense.common.pojo.base;

import java.io.Serializable;

public interface BaseEntity<Tid extends Object> extends Serializable {

    Tid getId();

    void setId(Tid id);

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    String getEntityType();
}
