package com.uet.towerdefense.common.pojo;

public class GameStage {

    private int hp;
    private int money;

    public GameStage(int hp, int money) {
        this.hp = hp;
        this.money = money;
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
}
