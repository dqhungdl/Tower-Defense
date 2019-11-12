package com.uet.towerdefense.common.pojo.cells;

import com.uet.towerdefense.common.enums.Cells;
import javafx.scene.Group;

public class RoadCell extends AbstractCell {

    @Override
    public String getCellType() {
        return Cells.ROAD;
    }

    @Override
    public void render(Group group) {

    }

    @Override
    public void update() {

    }
}
