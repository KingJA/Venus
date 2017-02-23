package lib.kingja.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Description：TODO
 * Create Time：2017/2/23 13:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MaterialProgress extends View {
    private final float MIN_ANGLE = 16f;
    private final int DEFAULT_PROGRESS_COLOR = 0xFF219F52;
    private float spinSpeed = 270f / 1000;
    private int mProgress = 0;
    private Paint barPaint = new Paint();
    private int width;
    private int height;
    private RectF circleBounds;
    private int offset;
    private boolean firstDraw = true;
    private int mRadius;
    private int mProgressColor;
    private int mProgressWidth;
    private int mSrokeCap;
    private long lastDrawTime = 0;

    public MaterialProgress(Context context) {
        this(context, null);
    }

    public MaterialProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MaterialProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialProgress);
        mRadius = (int) typedArray.getDimension(R.styleable.MaterialProgress_progressRadius, 0f);
        mRadius = dp2px(mRadius);
        mProgressWidth = (int) typedArray.getDimension(R.styleable.MaterialProgress_progressWidth, 0);
        mProgressWidth = dp2px(mProgressWidth);
        mProgressColor = typedArray.getColor(R.styleable.MaterialProgress_progressColor, DEFAULT_PROGRESS_COLOR);
        mSrokeCap = typedArray.getInteger(R.styleable.MaterialProgress_cycleStyle, 0);
        float mspinSpeed = typedArray.getFloat(R.styleable.MaterialProgress_spinSpeed, 0);


        lastDrawTime = SystemClock.uptimeMillis();
        invalidate();
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.e(TAG, "onVisibilityChanged: ");
        if (visibility == VISIBLE) {
            Log.e(TAG, "VISIBLE: ");
            lastDrawTime = SystemClock.uptimeMillis();
            barExtraLength = 0;
            timeStartGrowing = 0;
            barGrowingFromFront = true;
            pausedTimeWithoutGrowing = 0;
            firstDraw=true;
        }else{
            Log.e(TAG, "看不见: ");
        }
    }

    private int getProgressWidth() {
        return mProgressWidth == 0 ? (int) (width * 0.1f) : mProgressWidth;
    }

    protected int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    protected int sp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = mRadius + this.getPaddingLeft() + this.getPaddingRight();
        int viewHeight = mRadius + this.getPaddingTop() + this.getPaddingBottom();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(viewWidth, widthSize);
        } else {
            //Be whatever you want
            width = viewWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(viewHeight, heightSize);
        } else {
            //Be whatever you want
            height = viewHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = w;
        setPaint();
        setRect();
    }


    private void setRect() {
        circleBounds = new RectF(offset, offset, width - offset, height - offset);
    }

    private void setPaint() {
        //外圈画笔
        barPaint.setColor(mProgressColor);
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth(getProgressWidth());
        barPaint.setStrokeCap(mSrokeCap == 0 ? Paint.Cap.SQUARE : Paint.Cap.ROUND);
        offset = (int) (getProgressWidth() * 0.5f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long runTime = (SystemClock.uptimeMillis() - lastDrawTime);
        float runAngle = runTime * spinSpeed;//计算deltaTime这段时间经过的角度

        updateBarLength(runTime);

        mProgress += runAngle;
        if (mProgress > 360) {
            mProgress -= 360f;
        }

        float arcAngleStart = mProgress - 90;//从0度开始
        float arcAngle = MIN_ANGLE + barExtraLength;
//        Log.e(TAG, "runTime  : " + runTime + "  runAngle  : " + runAngle + "  mProgress  : " + mProgress + "  arcAngleStart   : " + arcAngleStart + " arcAngle : " + arcAngle);
        if (!firstDraw) {
            canvas.drawArc(circleBounds, arcAngleStart, arcAngle, false, barPaint);
            lastDrawTime = SystemClock.uptimeMillis();
        } else {
            firstDraw = false;
        }
        invalidate();

    }

    private void updateBarLength(long deltaTimeInMilliSeconds) {
        if (pausedTimeWithoutGrowing >= pauseGrowingTime) {
            timeStartGrowing += deltaTimeInMilliSeconds;

            if (timeStartGrowing > barSpinCycleTime) {
                // We completed a size change cycle
                // (growing or shrinking)
                timeStartGrowing -= barSpinCycleTime;
                //if(barGrowingFromFront) {
                pausedTimeWithoutGrowing = 0;
                //}
                barGrowingFromFront = !barGrowingFromFront;
            }

            float distance =
                    (float) Math.cos((timeStartGrowing / barSpinCycleTime + 1) * Math.PI) / 2 + 0.5f;
            float destLength = (barMaxLength - MIN_ANGLE);

            if (barGrowingFromFront) {
                barExtraLength = distance * destLength;
            } else {
                float newLength = destLength * (1 - distance);
                mProgress += (barExtraLength - newLength);
                barExtraLength = newLength;
            }
        } else {
            pausedTimeWithoutGrowing += deltaTimeInMilliSeconds;
        }
    }


    private final String TAG = MaterialProgress.this.getClass().getSimpleName();
    private final int barMaxLength = 270;
    private final long pauseGrowingTime = 200;
    private double barSpinCycleTime = 460;

    private float barExtraLength = 0;
    private double timeStartGrowing = 0;
    private boolean barGrowingFromFront = true;
    private long pausedTimeWithoutGrowing = 0;


}
