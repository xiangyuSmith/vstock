package com.vstock.db.entity;

import java.io.Serializable;

public class Point implements Serializable {

    private Long x;
    private int y;

    public Point() {
        super();
    }

    public Point(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
