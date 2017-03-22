package com.kingja.magicmirror;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Description：TODO
 * Create Time：2017/3/1421:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MagicMirror extends ImageView {

    private Bitmap mBitmap;
    private Bitmap mOutBitmap;
    private int corner;
    private int borderWidth;
    private int borderColor;
    private Paint borderPaint;
    private Mirror mirror;

    public MagicMirror(Context context) {
        this(context, null);
    }

    public MagicMirror(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicMirror(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MagicMirror);
        int sharpCode = typedArray.getInteger(R.styleable.MagicMirror_sharp, 0);
        corner = dp2px(typedArray.getDimension(R.styleable.MagicMirror_corner, 0));
        borderWidth = dp2px(typedArray.getDimension(R.styleable.MagicMirror_borderWidth, 0));
        borderColor = typedArray.getColor(R.styleable.MagicMirror_borderColor, 0xffffff);
        mirror = MirrorFactory.getMirror(sharpCode);
        //TODO 把参数都放入Mirror中
        typedArray.recycle();
    }

    private void initBitmap() {
        Drawable drawable = getDrawable();
        mBitmap = drawable2Bitmap(drawable);
        mOutBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOutBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //Dst
        mirror.drawSolid(canvas, getWidth(), getHeight(), corner);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //Src
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        paint.setXfermode(null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOutBitmap, 0, 0, null);
        mirror.drawStroke(canvas, getWidth(), getHeight(), corner, borderWidth, borderPaint);
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getWidth());
            drawable.draw(canvas);
        }
        return bitmap;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mirror.getMirrorMeasuredWidth(this), mirror.getMirrorMeasuredHeight(this));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initBitmap();
        initBorderPaint();
    }

    private void initBorderPaint() {
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);
    }

    protected int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
