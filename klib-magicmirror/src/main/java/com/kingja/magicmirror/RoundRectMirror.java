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
    private int mRoundRectMirrorWidth;
    private int mRoundRectMirrorHeight;
    @Override
    int getMeasuredMirrorWidth(MagicMirror magicMirror) {
        this.mRoundRectMirrorWidth=magicMirror.getMeasuredWidth();
        return mRoundRectMirrorWidth;
    }

    @Override
    int getMeasuredMirrorHeight(MagicMirror magicMirror) {
        this.mRoundRectMirrorHeight=magicMirror.getMeasuredHeight();
        return mRoundRectMirrorHeight;
    }

    @Override
    void drawSolid(Canvas canvas) {
        canvas.drawRoundRect(new RectF(0, 0, mRoundRectMirrorWidth, mRoundRectMirrorHeight), getCorner(), getCorner(), new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    @Override
    void drawStroke(Canvas canvas) {
        canvas.drawRoundRect(new RectF(getBorderWidth()*0.5f, getBorderWidth()*0.5f, mRoundRectMirrorWidth - getBorderWidth()*0.5f, mRoundRectMirrorHeight - getBorderWidth()*0.5f), (getCorner()*(mRoundRectMirrorWidth-getOffset()))/mRoundRectMirrorWidth, (getCorner()*(mRoundRectMirrorHeight-getOffset()))/mRoundRectMirrorHeight, getStrokePaint());
    }

    private int getOffset() {
        return (int) (0.5f*getBorderWidth());
    }

}
