package com.kingja.magicmirror;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
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
public class ShaderMirror extends ImageView {

    private Bitmap mBitmap;
    private Bitmap mOutBitmap;
    private int corner;
    private int borderWidth;
    private int borderColor;
    //    private int mWidth;
    //    private int mRadius;
    private Paint mBitmapPaint;

    public ShaderMirror(Context context) {
        this(context, null);
    }

    public ShaderMirror(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderMirror(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShaderMirror);
        int sharpCode = typedArray.getInteger(R.styleable.ShaderMirror_mirrorSharp, 0);
        corner = dp2px(typedArray.getDimension(R.styleable.ShaderMirror_mirrorCorner, 0));
        borderWidth = dp2px(typedArray.getDimension(R.styleable.ShaderMirror_mirrorBorderWidth, 0));
        borderColor = typedArray.getColor(R.styleable.ShaderMirror_mirrorBorderColor, 0xffffff);

        //TODO 把参数都放入Mirror中
        typedArray.recycle();
    }

    private void initBitmap() {
        Drawable drawable = getDrawable();
        mBitmap = drawable2Bitmap(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        BitmapShader mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        int bSize = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
        Matrix mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
//        mBitmapPaint.setColor(0xff123456);
//        mBitmapPaint.setStrokeWidth(borderWidth);
//        mBitmapPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        scale = getMeasuredWidth() * 1.0f / bSize;
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int offset = (int) (borderWidth * 0.5f);


        /*边框画笔*/
        Paint mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setStyle(Paint.Style.STROKE);
        mMaskPaint.setColor(borderColor);
        mMaskPaint.setStrokeWidth(borderWidth);
        /*边框路径*/
        Path mMaskPath = new Path();
        RectF mRectF = new RectF();
        mRectF.set(offset, offset, getMeasuredWidth() - offset, getMeasuredHeight() - offset);
        mMaskPath.addRoundRect(mRectF, corner, corner, Path.Direction.CW);

        canvas.drawPath(mMaskPath, mBitmapPaint);

        canvas.drawPath(mMaskPath, mMaskPaint);


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
//        mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
//        mRadius = mWidth / 2;
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initBitmap();
    }


    protected int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
