package com.kawka.badguide.ui.bottombarview;

import androidx.annotation.NonNull;

public class LineItem {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public LineItem(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public LineItem(int startX, float heightLineTop, int endX, float heightLineTop1) {
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    @NonNull
    @Override
    public String toString() {
        return "startX = " + getStartX() +"\n"
                + "startY = " + getStartY() +"\n"
                + "endX = " + getEndX() +"\n"
                + "endY = " + getEndY() +"\n";

    }
}
