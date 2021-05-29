package com.kawka.badguide.ui.bottombarview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.kawka.badguide.R;

import java.util.ArrayList;

public class BottomBarView extends View {

    private final String TAG = BottomBarView.class.getName();
    private int widthView;
    private int heightView;
    private int widthLine;
    private int heightLineTop =  2;

    private Paint paint;
    private Paint paintBitmap;



    private BitmapFactory.Options options;
    private Bitmap bitmap;

    public BottomBarView(Context context) {
        super(context);
        initPaint();
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drowLineTop(canvas);
        setDrawables(canvas);
        super.onDraw(canvas);
    }

    private void initPaint() {
        // init pain to lines
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.bottom_bar_line_top_3));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(heightLineTop);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);


        // init pain to drawables
        paintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        heightView = getResources().getDimensionPixelSize(R.dimen.slider_default_size);
        widthView = MeasureSpec.getSize(widthMeasureSpec);

        Log.d(TAG, "width = " + widthView + "   height = " + heightView);

        setMeasuredDimension(widthView, heightView);

    }
    /*
           Рисум верху бара три линии для эффекта
        */

    private void drowLineTop(Canvas canvas){

        ArrayList<LineItem> lineItems = new ArrayList<>();

       /*
            Вычесляем начало и конец линии для пяти линий
        */
        widthLine = widthView / 5; // 5 кнопок
        LineItem lineItem1 = new LineItem(0, 0, widthLine, 0);
        LineItem lineItem2 = new LineItem(widthLine , 0, widthLine * 2, 0);
        LineItem lineItem3 = new LineItem(widthLine * 2, 0, widthLine * 3, 0);
        LineItem lineItem4 = new LineItem(widthLine * 3, 0, widthLine * 4, 0);
        LineItem lineItem5 = new LineItem(widthLine * 4, 0, widthView, 0);

        lineItems.add(lineItem1);lineItems.add(lineItem2);lineItems.add(lineItem3);lineItems.add(lineItem4);lineItems.add(lineItem5);


        /*
            Рисуем линии
            первый слой
            Вычисляем координаты линий для нижнего слоя 2
         */


        ArrayList<LineItem> lineItemsTop2 = new ArrayList<>(); // лист для координат второго слоя

        for (LineItem item: lineItems
             ) {
            canvas.drawLine(item.getStartX(), item.getStartY(), item.getEndX(), item.getEndY(), paint);
            LineItem l = new LineItem(item.getStartX(), heightLineTop, item.getEndX(), heightLineTop);
            lineItemsTop2.add(l);
            item.toString();
        }

         /*
            Рисуем линии
            вьторой слой
            Вычисляем координаты линий для нижнего слоя 3
         */
        paint.setColor(getResources().getColor(R.color.bottom_bar_line_top_2));
        ArrayList<LineItem> lineItemsTop3 = new ArrayList<>(); // лист для координат третьего слоя

        for (LineItem item: lineItemsTop2
        ) {
            canvas.drawLine(item.getStartX(), item.getStartY(), item.getEndX(), item.getEndY(), paint);
            LineItem l = new LineItem(item.getStartX(), heightLineTop * 2, item.getEndX(), heightLineTop * 2);
            lineItemsTop3.add(l);
        }

        /*
            Рисуем линии
            третий слой
         */
        paint.setColor(getResources().getColor(R.color.bottom_bar_line_top_1));
        for (LineItem item: lineItemsTop3
        ) {
            canvas.drawLine(item.getStartX(), item.getStartY(), item.getEndX(), item.getEndY(), paint);
        }
    }

    /*
        Рисовалка меню (загружаем векторные картинки из ресурсов и рисуем их в центре каждого прямоугольника)

        -----------------  -----------------  -----------------  -----------------  -----------------
        |               |  |               |  |               |  |               |  |               |
        |               |  |               |  |               |  |               |  |               |
        |     icon1     |  |     icon2     |  |    icon3      |  |     icon4     |  |     icon5     |
        |               |  |               |  |               |  |               |  |               |
        |               |  |               |  |               |  |               |  |               |
        -----------------  -----------------  -----------------  -----------------  -----------------


     */

    private void setDrawables(Canvas canvas){
        /*
            Вычесляем координаты центров картинок
         */

        int centerY = heightView - (heightLineTop * 3);
        int icon1X = widthLine / 2;

        ArrayList<Bitmap> menu = new ArrayList<>();
        bitmap = drawableToBitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_info_24));
        //bitmap.setHeight(heightView / 2);
        //bitmap.setWidth(heightView / 2);
        menu.add(bitmap);

        for (Bitmap bm: menu
             ) {
            //canvas.drawARGB(10, 10, 10, 255);
            canvas.drawBitmap(bm, 0, 0, paintBitmap);
        }

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
