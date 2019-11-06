package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;

public class ObstacleCell extends AbstractCell {

    @Override
    public String getCellType() {
        return Cells.OBSTACLE;
    }
}