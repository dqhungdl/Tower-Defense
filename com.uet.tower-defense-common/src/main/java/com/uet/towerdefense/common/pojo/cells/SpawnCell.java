package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;

public class SpawnCell extends AbstractCell {

    @Override
    public String getCellType() {
        return Cells.SPAWN;
    }
}
