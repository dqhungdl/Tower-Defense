package com.uet.towerdefense.common.data;

public class Vector {

    private double dx;

    private double dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Vector(Vector vector) {
        this.dx = vector.dx;
        this.dy = vector.dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector temp = (Vector) obj;
            return temp.dx == dx && temp.dy == dy;
        }
        return false;
    }
}
