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
    protected Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    abstract int getMirrorMeasuredWidth(MagicMirror magicMirror);
    abstract int getMirrorMeasuredHeight(MagicMirror magicMirror);

    abstract void drawSolid(Canvas canvas, int width, int height,int corner);

    abstract void drawStroke(Canvas canvas, int width, int height, int corner,int borderWidth, Paint borderPaint);
}
