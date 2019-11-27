package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.Animations;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.GameStage;
import com.uet.towerdefense.common.pojo.towers.*;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MapService mapService;

    @Autowired
    private GamePlayScene gamePlayScene;

    private GameStage gameStage;

    private Text textMoney;
    private Text textHp;
    private Text textStage;
    private Text textTimer;

    public void init(GameStage gameStage) {
        this.gameStage = gameStage;
        ImageView imageViewMenu = new ImageView(AssetUtil.getBackgroundImage("1"));
        imageViewMenu.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE);
        imageViewMenu.setId(RenderLevels.MENU);
        nodeService.add(imageViewMenu);
        // Money text
        textMoney = new Text();
        textMoney.setText(String.valueOf(gameStage.getMoney()));
        textMoney.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 50);
        textMoney.setY(35);
        textMoney.setFont(Font.font(GamePlays.FONT, FontWeight.BOLD, 30));
        textMoney.setFill(Color.WHITE);
        textMoney.setId(RenderLevels.TEXT);
        nodeService.add(textMoney);
        // Hp text
        textHp = new Text();
        textHp.setText(String.valueOf(gameStage.getHp()));
        textHp.setX(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 50);
        textHp.setY(72);
        textHp.setFont(Font.font(GamePlays.FONT, FontWeight.BOLD, 30));
        textHp.setFill(Color.WHITE);
        textHp.setId(RenderLevels.TEXT);
        nodeService.add(textHp);
        // Stage text
        textStage = new Text();
        textStage.setText("Stage: " + gameStage.getStage());
        textStage.setX(10);
        textStage.setY(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE - 20);
        textStage.setFont(Font.font(GamePlays.FONT, FontWeight.BOLD, 30));
        textStage.setFill(Color.WHITE);
        textStage.setId(RenderLevels.TEXT);
        nodeService.add(textStage);
        // Timer text
        textTimer = new Text();
        textTimer.setText("Time: " + GamePlays.SECOND_START_GAME / GamePlays.SECOND_TO_MILLI);
        textTimer.setX(5 * GamePlays.SPRITE_SIZE);
        textTimer.setY(GamePlays.HEIGHT * GamePlays.SPRITE_SIZE - 20);
        textTimer.setFont(Font.font(GamePlays.FONT, FontWeight.BOLD, 30));
        textTimer.setFill(Color.WHITE);
        textTimer.setId(RenderLevels.TEXT);
        nodeService.add(textTimer);
        // Item's frame and tower's drag-drop
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110);
        createTowerImage(new SniperTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113, nodeService.getNodes()));
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110);
        createTowerImage(new MachineGunTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113, nodeService.getNodes()));
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new RocketTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 14, 113 + GamePlays.SPRITE_SIZE + 20, nodeService.getNodes()));
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 110 + GamePlays.SPRITE_SIZE + 20);
        createTowerImage(new AirGunTower(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 10, 113 + GamePlays.SPRITE_SIZE + 20, nodeService.getNodes()));
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 525);
        createUpdateButtonImage(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + 12, 525);
        createFrame(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 525);
        createSellButtonImage(GamePlays.WIDTH * GamePlays.SPRITE_SIZE + GamePlays.ADDED_WIDTH - GamePlays.SPRITE_SIZE - 12, 525);
        // Create save button
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
            nodeService.add(tempImageViewStand);
            nodeService.add(tempImageViewTower);
            nodeService.add(rangeCircle);
        });
        imageViewTower.setOnMouseReleased(mouseEvent -> {
            int x = (int) mouseEvent.getY() - GamePlays.TOWER_SIZE / 2;
            int y = (int) mouseEvent.getX() - GamePlays.TOWER_SIZE / 2;
            BaseTower addedTower = null;
            if (tower instanceof SniperTower)
                addedTower = new SniperTower(x, y, nodeService.getNodes());
            if (tower instanceof MachineGunTower)
                addedTower = new MachineGunTower(x, y, nodeService.getNodes());
            if (tower instanceof RocketTower)
                addedTower = new RocketTower(x, y, nodeService.getNodes());
            if (tower instanceof AirGunTower)
                addedTower = new AirGunTower(x, y, nodeService.getNodes());
            mapService.buyTower(addedTower);
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

    private void createSellButtonImage(int targetX, int targetY) {
        ImageView sellButton = new ImageView(AssetUtil.getImage("287"));
        sellButton.setX(targetX);
        sellButton.setY(targetY);
        ImageView tempSellButton = new ImageView(AssetUtil.getTowerImage("287"));
        tempSellButton.setId(RenderLevels.DRAG_DROP);
        sellButton.setOnMouseDragged(mouseEvent -> {
            int x = (int) mouseEvent.getY();
            int y = (int) mouseEvent.getX();
            List<BaseTower> towers = mapService.getTowers();
            for (BaseTower tower : towers) {
                if (tower.getX() <= x && x <= tower.getX() + GamePlays.TOWER_SIZE
                        && tower.getY() <= y && y <= tower.getY() + GamePlays.TOWER_SIZE) {
                    tower.getImageViewStand().setOpacity(Animations.DARK_OPACITY);
                    tower.getImageViewTower().setOpacity(Animations.DARK_OPACITY);
                } else {
                    tower.getImageViewStand().setOpacity(Animations.NORMAL_OPACITY);
                    tower.getImageViewTower().setOpacity(Animations.NORMAL_OPACITY);
                }
            }
            tempSellButton.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempSellButton.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            nodeService.add(tempSellButton);
        });
        sellButton.setOnMouseReleased(mouseEvent -> {
            int x = (int) mouseEvent.getY();
            int y = (int) mouseEvent.getX();
            List<BaseTower> towers = mapService.getTowers();
            for (BaseTower tower : towers) {
                if (tower.getX() <= x && x <= tower.getX() + GamePlays.TOWER_SIZE
                        && tower.getY() <= y && y <= tower.getY() + GamePlays.TOWER_SIZE) {
                    mapService.sellTower(tower);
                    break;
                }
            }
            nodeService.remove(tempSellButton);
            mouseEvent.setDragDetect(false);
        });
        sellButton.setId(RenderLevels.DRAG_DROP);
        nodeService.add(sellButton);
    }

    private void createUpdateButtonImage(int targetX, int targetY) {
        ImageView updateButton = new ImageView(AssetUtil.getImage("289"));
        updateButton.setX(targetX);
        updateButton.setY(targetY);
        ImageView tempUpdateButton = new ImageView(AssetUtil.getTowerImage("289"));
        tempUpdateButton.setId(RenderLevels.DRAG_DROP);
        updateButton.setOnMouseDragged(mouseEvent -> {
            int x = (int) mouseEvent.getY();
            int y = (int) mouseEvent.getX();
            for (BaseTower tower : mapService.getTowers()) {
                if (tower.getX() <= x && x <= tower.getX() + GamePlays.TOWER_SIZE
                        && tower.getY() <= y && y <= tower.getY() + GamePlays.TOWER_SIZE) {
                    tower.getImageViewStand().setOpacity(Animations.DARK_OPACITY);
                    tower.getImageViewTower().setOpacity(Animations.DARK_OPACITY);
                } else {
                    tower.getImageViewStand().setOpacity(Animations.NORMAL_OPACITY);
                    tower.getImageViewTower().setOpacity(Animations.NORMAL_OPACITY);
                }
            }
            tempUpdateButton.setX(mouseEvent.getX() - GamePlays.TOWER_SIZE / 2);
            tempUpdateButton.setY(mouseEvent.getY() - GamePlays.TOWER_SIZE / 2);
            nodeService.add(tempUpdateButton);
        });
        updateButton.setOnMouseReleased(mouseEvent -> {
            int x = (int) mouseEvent.getY();
            int y = (int) mouseEvent.getX();
            for (BaseTower tower : mapService.getTowers()) {
                if (tower.getX() <= x && x <= tower.getX() + GamePlays.TOWER_SIZE
                        && tower.getY() <= y && y <= tower.getY() + GamePlays.TOWER_SIZE) {
                    mapService.updateTower(tower);
                    break;
                }
            }
            nodeService.remove(tempUpdateButton);
            mouseEvent.setDragDetect(false);
        });
        updateButton.setId(RenderLevels.DRAG_DROP);
        nodeService.add(updateButton);
    }

    private void createFrame(int x, int y) {
        ImageView imageViewFrame = new ImageView(AssetUtil.getButtonImage("2", GamePlays.SPRITE_SIZE + 5, GamePlays.SPRITE_SIZE + 5));
        imageViewFrame.setX(x);
        imageViewFrame.setY(y);
        imageViewFrame.setId(RenderLevels.MENU);
        nodeService.add(imageViewFrame);
    }

    public boolean addMoney(int money) {
        if (gameStage.getMoney() + money < 0)
            return false;
        gameStage.setMoney(gameStage.getMoney() + money);
        return true;
    }

    public boolean subHp(int hp) {
        if (gameStage.getHp() < hp)
            return false;
        gameStage.setHp(gameStage.getHp() - hp);
        return true;
    }

    public void render() {
        textMoney.setText(String.valueOf(gameStage.getMoney()));
        textHp.setText(String.valueOf(gameStage.getHp()));
        textStage.setText("Stage: " + gameStage.getStage());
        if (gamePlayScene.getTimeElapse() / GamePlays.SECOND_TO_MILLI == 0)
            textTimer.setText(" Ready!");
        else
            textTimer.setText("Time: " + gamePlayScene.getTimeElapse() / GamePlays.SECOND_TO_MILLI);
    }
}
