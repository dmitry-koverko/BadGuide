package com.kawka.badguide.ui.bottombarview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.kawka.badguide.R;

import java.util.ArrayList;

public class BottomBarView extends ConstraintLayout implements View.OnClickListener {

    private final int animateDelay = 100;

    public enum State{
        INFO,
        FEED,
        REFRESH,
        IDEA,
        SETTINGS
    }

    private final String TAG = BottomBarView.class.getName();

    private ConstraintLayout itemInfo, itemFeed, itemRefresh, itemIdea, itemSettings;
    private View main;

    private State stateView = State.REFRESH;

    private TransitionDrawable transition;

    public BottomBarView(Context context) {
        super(context);
        init(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        //inflate(getContext(), R.layout.bottom_bar_view, this);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){
           main =  inflater.inflate(R.layout.bottom_bar_view, this);
        }

        if(main != null){

            itemInfo = main.findViewById(R.id.menu_item_info);
            itemFeed = main.findViewById(R.id.menu_item_list);
            itemRefresh = main.findViewById(R.id.menu_item_refresh);
            itemIdea = main.findViewById(R.id.menu_item_facts);
            itemSettings = main.findViewById(R.id.menu_item_settings);

            setClickListener();

            animateViewActive();

        }



    }

    private void setClickListener() {

        itemInfo.setOnClickListener(this);
        itemFeed.setOnClickListener(this);
        itemRefresh.setOnClickListener(this);
        itemIdea.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        animateResetActive();

        if(v.getId() == R.id.menu_item_info) stateView = State.INFO;
        else if(v.getId() == R.id.menu_item_list) stateView = State.FEED;
        else if(v.getId() == R.id.menu_item_refresh) stateView = State.REFRESH;
        else if(v.getId() == R.id.menu_item_facts) stateView = State.IDEA;
        else if(v.getId() == R.id.menu_item_settings) stateView = State.SETTINGS;

        mStateChangeListener.onChangeState(getState());

        animateViewActive();

    }

    private void animateViewActive(){

        View v = getSelectedView();

       if(v != null && v.getBackground() != null) {
           transition = (TransitionDrawable) v.getBackground();
           transition.startTransition(animateDelay);
       }


    }

    private void animateResetActive(){

        if(transition != null)
            transition.reverseTransition(10);

    }

    private View getSelectedView(){
       ConstraintLayout view;

       switch (stateView){
           case INFO:
               view = itemInfo;
               break;
           case FEED:
               view = itemFeed;
               break;
           case REFRESH:
               view = itemRefresh;
               break;
           case IDEA:
               view = itemIdea;
               break;
           case SETTINGS:
               view = itemSettings;
               break;
           default:
               view = itemRefresh;
               break;
       }

       return view;
    }

    private State getState(){
        return stateView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public interface BottomBarStateListener {
        public void onChangeState(State state);
    }

    private BottomBarStateListener mStateChangeListener;

    public void setEventListener(BottomBarStateListener mEventListener) {
        this.mStateChangeListener = mEventListener;
    }



}
