package com.kawka.badguide.ui.bottombarview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.kawka.badguide.R;

public class BottomBarItem extends View   {


    //    public interface IMyEventListener {
//        public void onEventOccurred();
//    }
//
//    private IMyEventListener mEventListener;
//
//    public void setEventListener(IMyEventListener mEventListener) {
//        this.mEventListener = mEventListener;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (mEventListener != null) {
//            mEventListener.onEventOccurred();
//        }
//        return true;
//    }

    private int imgSource;
    private boolean active;


    private final String TAG = BottomBarItem.class.getName();
    private int widthView;
    private int heightView;
    private int widthLine = 1;

    private Paint paint;
    private Paint paintBitmap;

    private int type = 0;

    private Path path;

    private Canvas mCanvas;

    private int cY = 0;

    public BottomBarItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BottomBarItem,
                0, 0);
        imgSource = R.drawable.ic_baseline_refresh_24;
        try {
            //imgSource = a.getInteger(R.styleable.BottomBarItem_android_src, R.drawable.ic_baseline_info_24);
            type = a.getInteger(R.styleable.BottomBarItem_type, 0);
            active = a.getBoolean(R.styleable.BottomBarItem_active, false);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            a.recycle();
        }

        initPaint();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(active) {
            drowLine(canvas);
        }
        drowIcon(canvas);
    }

    private void startAim() {

        ValueAnimator animation = ValueAnimator.ofInt(0 , 100);

        animation.setDuration(50);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               int animatedValue = (int) animation.getAnimatedValue();
               cY = animatedValue;
                invalidate();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        active = true;
        startAim();
        return true;
    }

    private void drowIcon(Canvas canvas) {

        Bitmap bitmap = null;
        switch (type){
            case 0: bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_info));
                break;
            case 1: bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_list_24));
                break;
            case 2: bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_refresh_24));
                break;
            case 3: bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_priority_high_24));
                break;
            case 4: bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_settings_24));
                break;
        }

        if(bitmap != null) Log.d(TAG, "viewBitmapHeight = " + bitmap.getHeight()  + " viewBitmapWidth = " + bitmap.getWidth());

        int centerBitmapY = (heightView - bitmap.getHeight()) /2;
        int centerBitmapX = (widthView - bitmap.getWidth()) / 2;

        Log.d(TAG, "centerX = " + centerBitmapX + "   centerY = " + centerBitmapY);

        canvas.drawBitmap(bitmap, centerBitmapX, centerBitmapY, paintBitmap);

    }

    private void drowLine(Canvas canvas) {

        try{
            paint.setStrokeWidth(6);
            paint.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawLine(0,0, 0, cY + 10, paint);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initPaint() {

        // init pain to lines
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.bottom_bar_line_top_1));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(widthLine);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        paintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = getResources().getDimensionPixelSize(R.dimen.slider_default_size);
        int desiredHeight = getResources().getDimensionPixelSize(R.dimen.slider_default_size);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        if (widthMode == MeasureSpec.EXACTLY) {
            widthView = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widthView = Math.min(desiredWidth, widthSize);
        } else {
            widthView = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            heightView = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            heightView = Math.min(desiredHeight, heightSize);
        } else {
            heightView = desiredHeight;
        }

        Log.d(TAG, "viewHeight = " + widthView  + " viewWidth = " + widthView);

        setMeasuredDimension(widthView, heightView);

    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
