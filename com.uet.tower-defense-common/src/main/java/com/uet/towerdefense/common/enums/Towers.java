package com.uet.towerdefense.common.enums;

public class Towers {
    public static final int ACCEPTABLE_PLACED_RANGE = 20;

    public static final String SNIPER = "Sniper";
    public static final String MACHINE_GUN = "Machine_Gun";
    public static final String ROCKET = "Rocket";
    public static final String AIR_GUN = "Air_Gun";

    public static final double[] SNIPER_SPEED = new double[]{2, 1.75, 1.5};
    public static final int[] SNIPER_RANGE = new int[]{250, 275, 350};
    public static final int[] SNIPER_DAMAGE = new int[]{3, 4, 5};
    public static final int[] SNIPER_MONEY = new int[]{15, 20, 30};

    public static final double[] MACHINE_GUN_SPEED = new double[]{1, 0.75, 0.5};
    public static final int[] MACHINE_GUN_RANGE = new int[]{150, 200, 250};
    public static final int[] MACHINE_GUN_DAMAGE = new int[]{1, 2, 3};
    public static final int[] MACHINE_GUN_MONEY = new int[]{10, 15, 25};

    public static final double[] ROCKET_SPEED = new double[]{2.75, 2.5, 2.25};
    public static final int[] ROCKET_RANGE = new int[]{200, 225, 250};
    public static final int[] ROCKET_DAMAGE = new int[]{2, 3, 4};
    public static final int[] ROCKET_MONEY = new int[]{30, 35, 40};

    public static final double[] AIR_GUN_SPEED = new double[]{1.25, 1, 0.5};
    public static final int[] AIR_GUN_RANGE = new int[]{200, 225, 300};
    public static final int[] AIR_GUN_DAMAGE = new int[]{2, 3, 8};
    public static final int[] AIR_GUN_MONEY = new int[]{25, 30, 50};
}
