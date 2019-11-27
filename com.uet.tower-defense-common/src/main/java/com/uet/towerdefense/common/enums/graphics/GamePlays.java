package com.uet.towerdefense.common.enums.graphics;

public class GamePlays {

    // Title
    public static final String TITLE = "Tower defense";

    // Game stage config
    public static final int BASE_MONEY = 100;
    public static final int BASE_HP = 100;
    public static final int BASE_STAGE = 25;

    // Sprite sizes config
    public static final int SPRITE_SIZE = 80;
    public static final int HEIGHT = 10;
    public static final int WIDTH = 12;
    public static final int ADDED_HEIGHT = 29;
    public static final int ADDED_WIDTH = 2 * SPRITE_SIZE + 50;
    public static final int TOWER_SIZE = SPRITE_SIZE;
    public static final int ENEMY_SIZE = SPRITE_SIZE;
    public static final int BULLET_SIZE = SPRITE_SIZE;

    // Timers
    public static final long SECOND_TO_MILLI = 1000L;
    public static final long SECOND_START_GAME = 10 * SECOND_TO_MILLI;
    public static final long SECOND_BETWEEN_STAGES = 5 * SECOND_TO_MILLI;

    // Text config
    public static final String FONT = "Courier New";
}