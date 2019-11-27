package com.uet.towerdefense.common.pojo;

public class GameStage {

    private int hp;
    private int money;
    private int stage;

    public GameStage(int hp, int money, int stage) {
        this.hp = hp;
        this.money = money;
        this.stage = stage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
