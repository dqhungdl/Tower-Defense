package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.MachineGunTower;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MapService mapService;

    private Group group;
    private List<NodeCompare> nodes;

    private void deleteDragDropItem() {
        for (int i = 0; i < nodes.size(); i++)
            if (nodes.get(i).getNode().getId().equals(RenderLevels.TEMP_DRAG_DROP))
                nodes.remove(i--);
            else if (nodes.get(i).getNode().getId().equals(RenderLevels.TEMP_DRAG_DROP))
                nodes.remove(i--);
        for (int i = 0; i < group.getChildren().size(); i++)
            if (group.getChildren().get(i).getId().equals(RenderLevels.TEMP_DRAG_DROP))
                group.getChildren().remove(i--);
            else if (group.getChildren().get(i).getId().equals(RenderLevels.TEMP_DRAG_DROP))
                group.getChildren().remove(i--);
    }

    private void createTowerImage(BaseTower tower) {
        ImageView imageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        imageViewTower.setX(tower.getX());
        imageViewTower.setY(tower.getY());
        ImageView imageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        imageViewStand.setX(tower.getX());
        imageViewStand.setY(tower.getY());
        ImageView tempImageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        tempImageViewStand.setId(RenderLevels.TEMP_DRAG_DROP);
        ImageView tempImageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        tempImageViewTower.setId(RenderLevels.TEMP_DRAG_DROP);
        Circle rangeCircle = new Circle();
        rangeCircle.setRadius(tower.getRange());
        rangeCircle.setFill(Color.BLUE);
        rangeCircle.setOpacity(0.3);
        rangeCircle.setId(RenderLevels.TEMP_DRAG_DROP);
        imageViewTower.setOnMouseDragged(mouseEvent -> {
            tempImageViewStand.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewStand.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            rangeCircle.setCenterX(mouseEvent.getX());
            rangeCircle.setCenterY(mouseEvent.getY());
            deleteDragDropItem();
            nodes.add(new NodeCompare(tempImageViewStand));
            nodes.add(new NodeCompare(tempImageViewTower));
            nodes.add(new NodeCompare(rangeCircle));
        });
        imageViewTower.setOnMouseReleased(mouseEvent -> {
            deleteDragDropItem();
            int x = (int) mouseEvent.getY() - GamePlays.TOWER_SIZE / 2;
            int y = (int) mouseEvent.getX() - GamePlays.TOWER_SIZE / 2;
            BaseTower addedTower = null;
            if (tower instanceof NormalTower)
                addedTower = new NormalTower(x, y);
            if (tower instanceof MachineGunTower)
                addedTower = new MachineGunTower(x, y);
            if (!mapService.addTower(addedTower)) {
                boolean isAdded = false;
                for (int distance = 1; distance <= Towers.ACCEPTABLE_PLACED_RANGE; distance++) {
                    if (isAdded)
                        break;
                    for (int i = x - distance; i <= x + distance; i++) {
                        addedTower.setX(i);
                        addedTower.setY(y - (distance - Math.abs(i - x)));
                        if (mapService.addTower(addedTower)) {
                            isAdded = true;
                            break;
                        }
                        addedTower.setX(i);
                        addedTower.setY(y + (distance - Math.abs(i - x)));
                        if (mapService.addTower(addedTower)) {
                            isAdded = true;
                            break;
                        }
                    }
                }
            }
            mouseEvent.setDragDetect(false);
        });
        imageViewStand.setId(RenderLevels.DRAG_DROP);
        imageViewTower.setId(RenderLevels.DRAG_DROP);
        nodes.add(new NodeCompare(imageViewStand));
        nodes.add(new NodeCompare(imageViewTower));
    }

    private void createTowerFrame(int x, int y) {
        ImageView imageViewFrame = new ImageView(AssetUtil.getButtonImage("2", GamePlays.SPRITE_SIZE + 5, GamePlays.SPRITE_SIZE + 5));
        imageViewFrame.setX(x);
        imageViewFrame.setY(y);
        imageViewFrame.setId(RenderLevels.MAP);
        nodes.add(new NodeCompare(imageViewFrame));
    }

    public void init(Group group, List<NodeCompare> nodes) {
        this.group = group;
        this.nodes = nodes;
        // Menu
        ImageView imageViewMenu = new ImageView(AssetUtil.getBackgroundImage("1"));
        imageViewMenu.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        imageViewMenu.setId(RenderLevels.MAP);
        nodes.add(new NodeCompare(imageViewMenu));
        // Item's frame and tower's drag-drop
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110);
        createTowerImage(new MachineGunTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113 + GamePlays.SPRITE_SIZE + 20));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113 + GamePlays.SPRITE_SIZE + 20));
    }

    public void render() {
    }
}
