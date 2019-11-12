package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;
import javafx.scene.Group;

public class ObstacleCell extends AbstractCell {

    @Override
    public String getCellType() {
        return Cells.OBSTACLE;
    }
}