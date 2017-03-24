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
    private int mCircleMirrorWidth;
    private int mCircleMirrorHeight;
    @Override
    int getMeasuredMirrorWidth(MagicMirror magicMirror) {
        this.mCircleMirrorWidth=Math.min(magicMirror.getMeasuredWidth(), magicMirror.getMeasuredHeight());
        return mCircleMirrorWidth;
    }

    @Override
    int getMeasuredMirrorHeight(MagicMirror magicMirror) {
        this.mCircleMirrorHeight=Math.min(magicMirror.getMeasuredWidth(), magicMirror.getMeasuredHeight());
        return mCircleMirrorHeight;
    }

    @Override
    void drawSolid(Canvas canvas) {
        canvas.drawCircle(mCircleMirrorWidth * 0.5f, mCircleMirrorHeight* 0.5f, mCircleMirrorWidth * 0.5f, new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    @Override
    void drawStroke(Canvas canvas) {
        canvas.drawCircle(mCircleMirrorWidth * 0.5f, mCircleMirrorWidth * 0.5f,mCircleMirrorWidth * 0.5f - getBorderWidth()*0.5f, getStrokePaint());
    }
}
