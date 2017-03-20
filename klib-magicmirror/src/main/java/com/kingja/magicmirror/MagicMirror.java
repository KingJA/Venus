package com.kingja.magicmirror;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

/**
 * Description：TODO
 * Create Time：2017/3/1421:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MagicMirror extends ImageView {

    private int width;
    private Bitmap mBitmap;
    private Bitmap mOutBitmap;
    private boolean isCircle;
    private int corner;
    private int borderWidth;
    private int borderOffset;
    private Paint borderPaint;
    private int borderColor;

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
        //画笔偏移
        borderOffset = (int) (borderWidth *0.5f);

        isCircle=sharpCode==0;
        typedArray.recycle();
    }

    private void initView() {
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        Log.e(TAG, "getWidth(): "+getWidth() );
        Drawable drawable = getDrawable();
        mBitmap = drawable2Bitmap(drawable);
        mOutBitmap = Bitmap.createBitmap(getWidth(), getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOutBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //Dst
        if (isCircle) {
            canvas.drawCircle(getWidth() * 0.5f, getWidth() * 0.5f, getWidth() * 0.5f, paint);
        }else{
            canvas.drawRoundRect(new RectF(0, 0, getWidth()-borderOffset,getHeight()-borderOffset),corner,corner, paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //Src
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        paint.setXfermode(null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: " );
        canvas.drawBitmap(mOutBitmap, 0, 0, null);
        if (isCircle) {
            canvas.drawCircle(getWidth() * 0.5f, getWidth() * 0.5f, getWidth() * 0.5f-borderOffset, borderPaint);
        }else{
            canvas.drawRoundRect(new RectF(borderOffset, borderOffset, getWidth()-borderOffset,getHeight()-borderOffset),corner,corner, borderPaint);
        }

    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                getWidth(),
                getWidth(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, getWidth(), getWidth());
        drawable.draw(canvas);
        return bitmap;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        if (isCircle) {
            setMeasuredDimension(size, size);
        } else{
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
        initPaint();
    }

    private void initPaint() {
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);
    }

    protected int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
