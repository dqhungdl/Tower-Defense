package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;

public class TargetCell extends AbstractCell {

    public TargetCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getCellType() {
        return Cells.TARGET;
    }
}