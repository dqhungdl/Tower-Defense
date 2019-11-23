package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.data.Coordinate;
import com.uet.towerdefense.common.data.NodeCompare;
import com.uet.towerdefense.common.enums.Cells;
import com.uet.towerdefense.common.enums.RenderLevels;
import com.uet.towerdefense.common.enums.graphics.Directions;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.enums.graphics.Maps;
import com.uet.towerdefense.common.pojo.bullets.BaseBullet;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.towers.BaseTower;
import com.uet.towerdefense.common.util.AssetUtil;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    private int mapId;
    private List<NodeCompare> nodes = new ArrayList<>();

    private List<BaseEnemy> enemies = new ArrayList<>();
    private List<BaseTower> towers = new ArrayList<>();
    private List<Coordinate> paths = new ArrayList<>();

    public void init(List<NodeCompare> nodes, int mapId) {
        this.nodes = nodes;
        this.mapId = mapId;
        ImageView imageView = new ImageView(AssetUtil.getMapImage(mapId));
        imageView.setId(RenderLevels.MAP);
        nodes.add(new NodeCompare(imageView));
        findPath(Maps.MAP_SPAWNS[mapId].getX(), Maps.MAP_SPAWNS[mapId].getY(), -1);
    }

    private void findPath(int x, int y, int lastDirection) {
        paths.add(new Coordinate(x, y));
        for (int i = 0; i < 4; i++)
            if ((i + 2) % 4 != lastDirection) {
                int newX = x + Directions.VECTORS[i].getDx();
                int newY = y + Directions.VECTORS[i].getDy();
                if (newX == Maps.MAP_TARGETS[mapId].getX() && newY == Maps.MAP_TARGETS[mapId].getY()) {
                    paths.add(new Coordinate(newX, newY));
                    return;
                }
                if (newX < 0 || newX >= GamePlays.HEIGHT || newY < 0 || newY >= GamePlays.WIDTH)
                    continue;
                if (Maps.MAP_SPRITES[mapId][newX][newY] == Cells.GRASS || Maps.MAP_SPRITES[mapId][newX][newY] == Cells.OBSTACLE)
                    continue;
                findPath(newX, newY, i);
                break;
            }
    }

    public void addEnemy(BaseEnemy enemy) {
        enemy.setX(Maps.MAP_SPAWNS[mapId].getX() * GamePlays.SPRITE_SIZE);
        enemy.setY(Maps.MAP_SPAWNS[mapId].getY() * GamePlays.SPRITE_SIZE);
        enemies.add(enemy);
    }

    public boolean addTower(BaseTower tower) {
        for (int i = tower.getX(); i < tower.getX() + GamePlays.TOWER_SIZE; i++)
            for (int j = tower.getY(); j < tower.getY() + GamePlays.TOWER_SIZE; j++) {
                Coordinate coordinate = new Coordinate(i / GamePlays.SPRITE_SIZE, j / GamePlays.SPRITE_SIZE);
                if (coordinate.getX() < 0 || coordinate.getX() >= GamePlays.HEIGHT || coordinate.getY() < 0 || coordinate.getY() >= GamePlays.WIDTH
                        || Maps.MAP_SPRITES[mapId][coordinate.getX()][coordinate.getY()] != Cells.GRASS)
                    return false;
            }
        for (BaseTower tempTower : towers) {
            int xMin = Math.max(tower.getX(), tempTower.getX());
            int yMin = Math.max(tower.getY(), tempTower.getY());
            int xMax = Math.min(tower.getX(), tempTower.getX());
            int yMax = Math.min(tower.getY(), tempTower.getY());
            if (xMin < xMax && yMin < yMax)
                return false;
        }
        towers.add(tower);
        return true;
    }

    public void render() {
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets)
                bullet.render(nodes);
            tower.render(nodes);
        }
        for (BaseEnemy enemy : enemies)
            enemy.render(nodes);
    }

    public void update(long latestTimestamp) {
        // update
        for (BaseEnemy enemy : enemies)
            enemy.update(paths);
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (BaseBullet bullet : bullets)
                bullet.update();
            tower.update(enemies, latestTimestamp);
        }
        // destroy
        for (BaseTower tower : towers) {
            List<BaseBullet> bullets = tower.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getX() == bullets.get(i).getTargetEnemy().getX()
                        && bullets.get(i).getY() == bullets.get(i).getTargetEnemy().getY()) {
                    bullets.get(i).getTargetEnemy().setHp(bullets.get(i).getTargetEnemy().getHp() - bullets.get(i).getDamage());
                    bullets.remove(i--);
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getX() == paths.get(paths.size() - 1).getX() * GamePlays.SPRITE_SIZE
                    && enemies.get(i).getY() == paths.get(paths.size() - 1).getY() * GamePlays.SPRITE_SIZE)
                enemies.remove(i--);
            else if (enemies.get(i).getHp() <= 0)
                enemies.remove(i--);
    }
}
