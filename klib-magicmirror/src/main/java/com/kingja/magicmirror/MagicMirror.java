package com.kingja.magicmirror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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

    public MagicMirror(Context context) {
        this(context, null);
    }

    public MagicMirror(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicMirror(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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
        canvas.drawCircle(getWidth() * 0.5f, getWidth() * 0.5f, getWidth() * 0.5f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        //Src
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        paint.setXfermode(null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: " );
        canvas.drawBitmap(mOutBitmap, 0, 0, null);
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
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
    }
}
