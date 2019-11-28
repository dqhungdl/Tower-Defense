package com.uet.towerdefense.common.enums;

public class Enemies {
    public static final String SMALL = "Small";
    public static final String TANK = "Tank";
    public static final String PLANE = "Plane";

    public static final int[] SMALL_HP = {1, 3, 5, 7};
    public static final double[] SMALL_SPEED = {0.5, 0.75, 1.0, 1.5};
    public static final int[] SMALL_DEFENSE = {0, 1, 2, 3};
    public static final int[] SMALL_MONEY = {1, 2, 2, 3};

    public static final int[] TANK_HP = {2, 5};
    public static final double[] TANK_SPEED = {0.5, 0.75};
    public static final int[] TANK_DEFENSE = {1, 3};
    public static final int[] TANK_MONEY = {1, 3};

    public static final int[] PLANE_HP = {2, 5};
    public static final double[] PLANE_SPEED = {0.5, 0.7};
    public static final int[] PLANE_DEFENSE = {1, 3};
    public static final int[] PLANE_MONEY = {1, 5};
}
