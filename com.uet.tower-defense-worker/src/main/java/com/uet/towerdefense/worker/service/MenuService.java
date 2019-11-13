package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.pojo.towers.NormalTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MapService mapService;

    private Group group;
    private List<NodeCompare> nodes;

    public void init(Group group, List<NodeCompare> nodes) {
        this.group = group;
        this.nodes = nodes;
        createItem(new NormalTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE, 0));
    }

    private void deleteDragDropItem() {
        for (int i = 0; i < nodes.size(); i++)
            if (nodes.get(i).getNode().getId().equals(RenderLevels.DRAG_DROP_STAND))
                nodes.remove(i--);
            else if (nodes.get(i).getNode().getId().equals(RenderLevels.DRAG_DROP_TOWER))
                nodes.remove(i--);
        for (int i = 0; i < group.getChildren().size(); i++)
            if (group.getChildren().get(i).getId().equals(RenderLevels.DRAG_DROP_STAND))
                group.getChildren().remove(i--);
            else if (group.getChildren().get(i).getId().equals(RenderLevels.DRAG_DROP_TOWER))
                group.getChildren().remove(i--);
    }

    private void createItem(BaseTower tower) {
        ImageView imageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        imageViewTower.setX(tower.getX());
        imageViewTower.setY(tower.getY());
        ImageView imageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        imageViewStand.setX(tower.getX());
        imageViewStand.setY(tower.getY());
        ImageView tempImageViewStand = new ImageView(AssetUtil.getTowerImage(tower.getStandImageId()));
        tempImageViewStand.setId(RenderLevels.DRAG_DROP_STAND);
        ImageView tempImageViewTower = new ImageView(AssetUtil.getTowerImage(tower.getTowerImageId()));
        tempImageViewTower.setId(RenderLevels.DRAG_DROP_TOWER);
        imageViewTower.setOnMouseDragged(mouseEvent -> {
            tempImageViewStand.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewStand.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempImageViewTower.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            deleteDragDropItem();
            nodes.add(new NodeCompare(tempImageViewStand));
            nodes.add(new NodeCompare(tempImageViewTower));
        });
        imageViewTower.setOnMouseReleased(event -> {
            deleteDragDropItem();
            int x = (int) (event.getY() - GamePlays.TOWER_SIZE / 2) / GamePlays.SPRITE_SIZE * GamePlays.SPRITE_SIZE;
            int y = (int) (event.getX() - GamePlays.TOWER_SIZE / 2) / GamePlays.SPRITE_SIZE * GamePlays.SPRITE_SIZE;
            BaseTower addedTower = null;
            if (tower instanceof NormalTower)
                addedTower = new NormalTower(x, y);
            mapService.addTower(addedTower);
            event.setDragDetect(false);
        });
        imageViewStand.setId(RenderLevels.DRAG_DROP);
        nodes.add(new NodeCompare(imageViewStand));
        imageViewTower.setId(RenderLevels.DRAG_DROP);
        nodes.add(new NodeCompare(imageViewTower));
    }

    public void render() {
        Rectangle rectangle = new Rectangle(GamePlays.WIDTH * GamePlays.SPRITE_SIZE, 0, GamePlays.ADDED_WIDTH, GamePlays.HEIGHT * GamePlays.SPRITE_SIZE);
        rectangle.setFill(Color.WHEAT);
        rectangle.setId(RenderLevels.OBJECT);
        nodes.add(new NodeCompare(rectangle));
    }
}
