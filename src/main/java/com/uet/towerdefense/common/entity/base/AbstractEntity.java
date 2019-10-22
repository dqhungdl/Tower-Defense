package com.uet.towerdefense.common.entity.base;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class AbstractEntity<Tid extends Object> implements BaseEntity<Tid> {

    @Id
    @Field(value = "id")
    private Tid id;

    @Override
    public Tid getId() {
        return id;
    }

    @Override
    public void setId(Tid id) {
        this.id = id;
    }
}
