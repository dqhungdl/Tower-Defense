package com.uet.towerdefense.common.data;

public class Vector {

    private int dx;

    private int dy;

    public Vector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Vector(Vector vector) {
        this.dx = vector.dx;
        this.dy = vector.dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
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
