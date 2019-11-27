package com.uet.towerdefense.common.enums;

public class Towers {
    public static final int ACCEPTABLE_PLACED_RANGE = 20;

    public static final String SNIPER = "Normal";
    public static final String MACHINE_GUN = "Machine Gun";
    public static final String ROCKET = "Rocket";

    public static final double[] SNIPER_SPEED = new double[]{2.5, 2.5, 2.5};
    public static final int[] SNIPER_RANGE = new int[]{300, 300, 300};
    public static final int[] SNIPER_DAMAGE = new int[]{3, 3, 3};
    public static final int[] SNIPER_MONEY = new int[]{10, 10, 10};

    public static final double[] MACHINE_GUN_SPEED = new double[]{0.5, 0.5, 0.5};
    public static final int[] MACHINE_GUN_RANGE = new int[]{150, 150, 150};
    public static final int[] MACHINE_GUN_DAMAGE = new int[]{1, 1, 1};
    public static final int[] MACHINE_GUN_MONEY = new int[]{25, 25, 25};

    public static final double[] ROCKET_SPEED = new double[]{2.5, 2.5, 2.5};
    public static final int[] ROCKET_RANGE = new int[]{300, 300, 300};
    public static final int[] ROCKET_DAMAGE = new int[]{3, 3, 3};
    public static final int[] ROCKET_MONEY = new int[]{10, 10, 10};
}
