package com.kingja.magicmirror;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Description：TODO
 * Create Time：2017/3/2221:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class Mirror {
    private int corner;
    private int borderWidth;
    private int borderColor;
    private int width;
    private int height;

    public int getHeight() {
        return height;
    }

    public Mirror setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Mirror setWidth(int width) {
        this.width = width;
        return this;
    }


    public int getCorner() {
        return corner;
    }

    public Mirror setCorner(int corner) {
        this.corner = corner;
        return this;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public Mirror setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public Mirror setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Paint getStrokePaint() {
        Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setColor(getBorderColor());
        strokePaint.setStrokeWidth(getBorderWidth());
        strokePaint.setStyle(Paint.Style.STROKE);
        return strokePaint;
    }


    abstract int getMeasuredMirrorWidth(MagicMirror magicMirror);

    abstract int getMeasuredMirrorHeight(MagicMirror magicMirror);

    abstract void drawSolid(Canvas canvas);

    abstract void drawStroke(Canvas canvas);
}
