package com.uet.towerdefense.worker.service;

import com.uet.towerdefense.common.enums.Enemies;
import com.uet.towerdefense.common.enums.Towers;
import com.uet.towerdefense.common.enums.graphics.GamePlays;
import com.uet.towerdefense.common.pojo.enemies.BaseEnemy;
import com.uet.towerdefense.common.pojo.enemies.PlaneEnemy;
import com.uet.towerdefense.common.pojo.enemies.SmallEnemy;
import com.uet.towerdefense.common.pojo.enemies.TankEnemy;
import com.uet.towerdefense.common.pojo.towers.*;
import com.uet.towerdefense.worker.scene.GamePlayScene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

@Service
public class ResumeService {

    @Autowired
    private MapService mapService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private GamePlayScene gamePlayScene;

    @Autowired
    private NotificationService notificationService;

    private PrintWriter getPrintWriter(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            File directory = new File(file.getParent());
            if (!directory.exists())
                directory.mkdirs();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        return new PrintWriter(fileWriter);
    }

    public void store() {
        try {
            // Save game stage
            PrintWriter printWriter = getPrintWriter("resources/gamestage");
            printWriter.println(menuService.getGameStage().getHp() + " " + menuService.getGameStage().getMoney() + " " + menuService.getGameStage().getStage());
            printWriter.close();
            // Save towers
            printWriter = getPrintWriter("resources/towers");
            for (BaseTower tower : mapService.getTowers())
                printWriter.println(tower.getX() + " " + tower.getY() + " " + tower.getLevel() + " " + tower.getDirection() + " " + tower.getTowerType());
            printWriter.close();
            // Save enemies
            printWriter = getPrintWriter("resources/enemies");
            for (BaseEnemy enemy : mapService.getEnemies())
                printWriter.println(enemy.getX() + " " + enemy.getY() + " " + enemy.getHp() + " " + enemy.getLevel() + " " + enemy.getDirection() + " " + enemy.getEnemyType());
            for (BaseEnemy enemy : gamePlayScene.getEnemies())
                printWriter.println(enemy.getLevel() + " " + enemy.getEnemyType());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            // Load game stage
            File file = new File("resources/gamestage");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(" ");
                int hp = Integer.parseInt(str[0]);
                int money = Integer.parseInt(str[1]);
                int stage = Integer.parseInt(str[2]);
                menuService.getGameStage().setHp(hp);
                menuService.getGameStage().setMoney(money);
                menuService.getGameStage().setStage(stage);
            }
            // Load towers
            file = new File("resources/towers");
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(" ");
                double x = Double.parseDouble(str[0]);
                double y = Double.parseDouble(str[1]);
                int level = Integer.parseInt(str[2]);
                int direction = Integer.parseInt(str[3]);
                String towerType = str[4];
                BaseTower tower = null;
                if (towerType.equals(Towers.SNIPER))
                    tower = new SniperTower(x, y, nodeService.getNodes(), notificationService.getNotification());
                if (towerType.equals(Towers.MACHINE_GUN))
                    tower = new MachineGunTower(x, y, nodeService.getNodes(), notificationService.getNotification());
                if (towerType.equals(Towers.ROCKET))
                    tower = new RocketTower(x, y, nodeService.getNodes(), notificationService.getNotification());
                if (towerType.equals(Towers.AIR_GUN))
                    tower = new AirGunTower(x, y, nodeService.getNodes(), notificationService.getNotification());
                tower.setDirection(direction);
                tower.setLevel(level);
                mapService.getTowers().add(tower);
                nodeService.add(tower.getImageViewStand());
                nodeService.add(tower.getImageViewTower());
            }
            // Load enemies
            file = new File("resources/enemies");
            scanner = new Scanner(file);
            boolean isExistedEnemy = false;
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(" ");
                if (str.length == 6) {
                    isExistedEnemy = true;
                    double x = Double.parseDouble(str[0]);
                    double y = Double.parseDouble(str[1]);
                    int hp = Integer.parseInt(str[2]);
                    int level = Integer.parseInt(str[3]);
                    int direction = Integer.parseInt(str[4]);
                    String enemyType = str[5];
                    BaseEnemy enemy = null;
                    if (enemyType.equals(Enemies.SMALL))
                        enemy = new SmallEnemy(level);
                    if (enemyType.equals(Enemies.TANK))
                        enemy = new TankEnemy(level);
                    if (enemyType.equals(Enemies.PLANE))
                        enemy = new PlaneEnemy(level);
                    enemy.setX(x);
                    enemy.setY(y);
                    enemy.setHp(hp);
                    enemy.setDirection(direction);
                    mapService.getEnemies().add(enemy);
                    nodeService.add(enemy.getImageView());
                } else {
                    int level = Integer.parseInt(str[0]);
                    String enemyType = str[1];
                    BaseEnemy enemy = null;
                    if (enemyType.equals(Enemies.SMALL))
                        enemy = new SmallEnemy(level);
                    if (enemyType.equals(Enemies.TANK))
                        enemy = new TankEnemy(level);
                    if (enemyType.equals(Enemies.PLANE))
                        enemy = new PlaneEnemy(level);
                    gamePlayScene.addEnemy(enemy);
                }
            }
            // Update timestamp
            if (!isExistedEnemy)
                gamePlayScene.setNextTimestamp(new Date().getTime() + GamePlays.SECOND_START_GAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
