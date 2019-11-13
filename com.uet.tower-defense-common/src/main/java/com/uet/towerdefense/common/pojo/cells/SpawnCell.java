package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;

public class SpawnCell extends AbstractCell {

    public SpawnCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getCellType() {
        return Cells.SPAWN;
    }
}
