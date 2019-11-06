package com.uet.towerdefense.common.pojo.base;

public interface BaseEntity<Tid extends Object> {

    Tid getId();

    void setId(Tid id);

    String getEntityType();

    void setEntityType(String entityType);
}
