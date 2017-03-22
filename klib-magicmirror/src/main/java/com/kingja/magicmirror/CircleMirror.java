package com.kingja.magicmirror;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Description：TODO
 * Create Time：2017/3/2221:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CircleMirror extends Mirror {

    @Override
    int getMirrorMeasuredWidth(MagicMirror magicMirror) {
        return Math.min(magicMirror.getMeasuredWidth(), magicMirror.getMeasuredHeight());
    }

    @Override
    int getMirrorMeasuredHeight(MagicMirror magicMirror) {
        return Math.min(magicMirror.getMeasuredWidth(), magicMirror.getMeasuredHeight());
    }

    @Override
    void drawSolid(Canvas canvas, int width, int height,int corner) {
        canvas.drawCircle(width * 0.5f, height* 0.5f, width * 0.5f, paint);
    }

    @Override
    void drawStroke(Canvas canvas, int width, int height, int corner, int borderWidth, Paint borderPaint) {
        canvas.drawCircle(width * 0.5f, width * 0.5f,width * 0.5f - borderWidth*0.5f, borderPaint);
    }
}
