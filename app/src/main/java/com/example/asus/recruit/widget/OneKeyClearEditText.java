package com.example.asus.recruit.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.asus.recruit.R;

public class OneKeyClearEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {

    private Drawable mClearDrawable;
    private boolean hasFocus;// 控件是否有焦点

    //editText滑动方法2
//    //滑动距离的最大边界
//    private int mOffsetHeight;
//
//    //是否到顶或者到底的标志
//    private boolean mBottomFlag = false;

    //editText滑动方法4
    private final int MOVE_SLOP = 50; //移动距离临界

    //滑动距离的最大边界
    private int mOffsetHeight;

    //是否到顶或者到底的标志
    private boolean mBottomFlag = false;
    private boolean isCanScroll = false;//标记内容是否触发了滚动
    private float lastY = 0;


    public OneKeyClearEditText(Context context) {
        this(context, null);
    }

    public OneKeyClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int paddingTop;
        int paddingBottom;
        int mHeight;
        int mLayoutHeight;

        //获得内容面板
        Layout mLayout = getLayout();
        //获得内容面板的高度
        mLayoutHeight = mLayout.getHeight();
        //获取上内边距
        paddingTop = getTotalPaddingTop();
        //获取下内边距
        paddingBottom = getTotalPaddingBottom();

        //获得控件的实际高度
        mHeight = getHeight();

        //计算滑动距离的边界
        mOffsetHeight = mLayoutHeight + paddingTop + paddingBottom - mHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //手指按下事件，重置状态
            mBottomFlag = false;
            isCanScroll = false;
            lastY = 0;
        }
        return super.dispatchTouchEvent(event);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN)
//            //如果是新的按下事件，则对mBottomFlag重新初始化
//            mBottomFlag = false;
//        //如果已经不要这次事件，则传出取消的信号，这里的作用不大
//        if (mBottomFlag)
//            event.setAction(MotionEvent.ACTION_CANCEL);
//        return super.dispatchTouchEvent(event);
//    }

    public OneKeyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initClearDrawable(context);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    private void initClearDrawable(Context context) {

        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mClearDrawable = getResources().getDrawable(R.drawable.button_clearing, context.getTheme());
            }else {
                mClearDrawable = getResources().getDrawable(R.drawable.button_clearing);
            }
            DrawableCompat.setTint(mClearDrawable, getCurrentHintTextColor());
            mClearDrawable.setBounds(0, 0, (int) getTextSize() - 3, (int) getTextSize() - 3);
        }
    }


    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(l);
    }

    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mClearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            // 判断触摸点是否在水平范围内
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight())) && (x < (getWidth() - getPaddingRight()));
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = mClearDrawable.getBounds();
            // 获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerHeight && isInnerWidth) {
                this.setText("");
            }
        }

//        //如果是需要拦截，则再拦截，这个方法会在onScrollChanged方法之后再调用一次
//        if (!mBottomFlag) {
//            getParent().requestDisallowInterceptTouchEvent(true);
//        }

        //如果是需要拦截，则再拦截，这个方法会在onScrollChanged方法之后再调用一次
        if (!mBottomFlag)
            getParent().requestDisallowInterceptTouchEvent(true);
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            if (lastY == 0){
                lastY = event.getRawY();
            }
            //条件：手指move了一段距离，但是onScrollChanged函数未调用，说明文字无法滚动了，则将触摸处理权交还给ParentView
            if (Math.abs(lastY - event.getRawY()) > MOVE_SLOP){
                if (!isCanScroll){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            //Log.d("Javine","ActionMove: "+ lastY + "," + event.getRawY());
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
        isCanScroll = true;
        //Log.d("Javine","onScrolled "+vert);
        if (vert == mOffsetHeight || vert == 0) {
            //这里将处理权交还给父控件
            getParent().requestDisallowInterceptTouchEvent(false);
            mBottomFlag = true;
        }
    }

//    @Override
//    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
//        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
//        if (vert == mOffsetHeight || vert == 0) {
//            //这里触发父布局或祖父布局的滑动事件
//            getParent().requestDisallowInterceptTouchEvent(false);
//            mBottomFlag = true;
//        }
//    }


    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        CardView cardView = (CardView)getParent();
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);

            cardView.setCardElevation(15.0f);
        } else {
            setClearIconVisible(false);
            cardView.setCardElevation(0.0f);
        }
    }
}
