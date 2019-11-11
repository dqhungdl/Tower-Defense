package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.pojo.base.AbstractStaticEntity;

public abstract class AbstractCell extends AbstractStaticEntity<Long> implements BaseCell<Long> {

    public abstract String getCellType();
}
