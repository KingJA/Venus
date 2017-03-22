package com.kingja.magicmirror;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Description：TODO
 * Create Time：2017/3/2221:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RoundRectMirror extends Mirror {
    @Override
    int getMirrorMeasuredWidth(MagicMirror magicMirror) {
        return magicMirror.getMeasuredWidth();
    }

    @Override
    int getMirrorMeasuredHeight(MagicMirror magicMirror) {
        return magicMirror.getMeasuredHeight();
    }

    @Override
    void drawSolid(Canvas canvas, int width, int height,int corner) {
        canvas.drawRoundRect(new RectF(0, 0, width, height), corner, corner, paint);
    }

    @Override
    void drawStroke(Canvas canvas, int width, int height, int corner, int borderWidth, Paint borderPaint) {
        canvas.drawRoundRect(new RectF(borderWidth*0.5f, borderWidth*0.5f, width - borderWidth*0.5f, height - borderWidth*0.5f), corner, corner, borderPaint);
    }
}
