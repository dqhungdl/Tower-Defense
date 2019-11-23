package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.MachineGunTower;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MapService mapService;

    private void deleteDragDropItem() {
    }

    private void createTowerImage(BaseTower tower) {
        ImageView imageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        imageViewTower.setX(tower.getX());
        imageViewTower.setY(tower.getY());
        ImageView imageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        imageViewStand.setX(tower.getX());
        imageViewStand.setY(tower.getY());
        ImageView tempImageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        tempImageViewStand.setId(RenderLevels.DRAG_DROP);
        ImageView tempImageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        tempImageViewTower.setId(RenderLevels.DRAG_DROP);
        Circle rangeCircle = new Circle();
        rangeCircle.setRadius(tower.getRange());
        rangeCircle.setFill(Color.BLUE);
        rangeCircle.setOpacity(Animations.LIGHT_OPACITY);
        rangeCircle.setId(RenderLevels.ANIMATION);
        imageViewTower.setOnMouseDragged(mouseEvent -> {
            tempImageViewStand.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewStand.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            rangeCircle.setCenterX(mouseEvent.getX());
            rangeCircle.setCenterY(mouseEvent.getY());
            nodeService.remove(tempImageViewStand);
            nodeService.remove(tempImageViewTower);
            nodeService.remove(rangeCircle);
            nodeService.add(tempImageViewStand);
            nodeService.add(tempImageViewTower);
            nodeService.add(rangeCircle);
        });
        imageViewTower.setOnMouseReleased(mouseEvent -> {
            deleteDragDropItem();
            int x = (int) mouseEvent.getY() - GamePlays.TOWER_SIZE / 2;
            int y = (int) mouseEvent.getX() - GamePlays.TOWER_SIZE / 2;
            BaseTower addedTower = null;
            if (tower instanceof NormalTower)
                addedTower = new NormalTower(x, y, nodeService.getNodes());
            if (tower instanceof MachineGunTower)
                addedTower = new MachineGunTower(x, y, nodeService.getNodes());
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
            nodeService.remove(tempImageViewStand);
            nodeService.remove(tempImageViewTower);
            nodeService.remove(rangeCircle);
            mouseEvent.setDragDetect(false);
        });
        imageViewStand.setId(RenderLevels.DRAG_DROP);
        imageViewTower.setId(RenderLevels.DRAG_DROP);
        nodeService.add(imageViewStand);
        nodeService.add(imageViewTower);
    }

    private void createTowerFrame(int x, int y) {
        ImageView imageViewFrame = new ImageView(AssetUtil.getButtonImage("2", GamePlays.SPRITE_SIZE + 5, GamePlays.SPRITE_SIZE + 5));
        imageViewFrame.setX(x);
        imageViewFrame.setY(y);
        imageViewFrame.setId(RenderLevels.MENU);
        nodeService.add(imageViewFrame);
    }

    public void init() {
        // Menu
        ImageView imageViewMenu = new ImageView(AssetUtil.getBackgroundImage("1"));
        imageViewMenu.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        imageViewMenu.setId(RenderLevels.MENU);
        nodeService.add(imageViewMenu);
        // Item's frame and tower's drag-drop
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113, nodeService.getNodes()));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110);
        createTowerImage(new MachineGunTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113, nodeService.getNodes()));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113 + GamePlays.SPRITE_SIZE + 20, nodeService.getNodes()));
        createTowerFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113 + GamePlays.SPRITE_SIZE + 20, nodeService.getNodes()));
    }

    public void render() {
    }
}
