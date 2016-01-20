package com.hanwencheng;

/**
 * Created by hanwencheng on 1/16/16.
 * A object for testing storing custom object into nodes
 */
public class Position {
    public int x;
    public int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
