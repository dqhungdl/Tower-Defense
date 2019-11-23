package com.uet.towerdefense.common.enums;

public class Towers {
    public static final int ACCEPTABLE_PLACED_RANGE = 20;

    public static final String NORMAL = "Normal";
    public static final String SNIPER = "Sniper";
    public static final String MACHINE_GUN = "Machine Gun";

    public static final double[] NORMAL_SPEED = new double[]{2.5, 2.5};
    public static final int[] NORMAL_RANGE = new int[]{300, 300};
    public static final int[] NORMAL_DAMAGE = new int[]{3, 3};
    public static final int[] NORMAL_GOLD = new int[]{10, 10};

    public static final double[] MACHINE_GUN_SPEED = new double[]{0.5, 0.5};
    public static final int[] MACHINE_GUN_RANGE = new int[]{150, 150};
    public static final int[] MACHINE_GUN_DAMAGE = new int[]{1, 1};
    public static final int[] MACHINE_GUN_GOLD = new int[]{25, 25};
}
