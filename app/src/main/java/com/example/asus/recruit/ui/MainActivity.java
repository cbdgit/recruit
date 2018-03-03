package com.example.asus.recruit.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import com.example.asus.recruit.R;
import com.example.asus.recruit.adapter.FragmentAdapter;
import com.example.asus.recruit.widget.CustPagerTransformer;

import com.yasic.bubbleview.BubbleView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.os.Build;

import android.support.v4.view.ViewPager;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import tyrantgit.explosionfield.ExplosionField;

import static com.example.asus.recruit.configs.Content.IMAGE_ID;
import static com.example.asus.recruit.configs.Content.NAME;
import static com.example.asus.recruit.configs.Content.SUMMARY;

public class MainActivity extends AppCompatActivity {

    private View mPositionView;
    private ViewPager mViewPager;
    private List<CommonFragment> mFragments;
    private BubbleView mBubbleView;
    private FragmentAdapter mAdapter;
    private ImageView mImageView;

    private Handler mHandler;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initView();

       initData();





    }



    private void initView(){

        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow()
                        .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }


        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mPositionView = findViewById(R.id.position_view);
        mBubbleView = (BubbleView) findViewById(R.id.BubbleView);
        mImageView = (ImageView)findViewById(R.id.iv_baoming);
        final ExplosionField field = ExplosionField.attach2Window(this);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // field.explode(mImageView);
                Intent intent = new Intent(MainActivity.this,RecruitActivity.class);
                startActivity(intent);




            }
        });










       initBubbleView();
        dealStatusBar();

    }


    private void reset(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }


    /**
     * 初始BubbleView的设置
     */
    private void initBubbleView(){
        List<Drawable> drawableList = new ArrayList<>();
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        drawableList.add(getResources().getDrawable(R.drawable.bubble_two));
        mBubbleView.setDrawableList(drawableList);
        mBubbleView.setMaxHeartNum(15);
        mBubbleView.setMinHeartNum(5);
        mBubbleView.setBottomPadding(40);
        mBubbleView.setItemViewWH(90,90);
        ViewTreeObserver vto = mBubbleView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                final int height =mBubbleView.getHeight();
                final int width =mBubbleView.getWidth();
                mBubbleView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (height!=0&&width!=0){

             //   mBubbleView.startAnimation(width,height);
                    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                        }

                        @Override
                        public void onPageSelected(int position) {
                            reset(mImageView);
                            //mBubbleView.startAnimation(width,height);
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });



                }
            }
        });



    }





    /**
     * 调整沉浸式菜单的title
     */
    private void dealStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight();
            ViewGroup.LayoutParams lp = mPositionView.getLayoutParams();
            lp.height = statusBarHeight;
            mPositionView.setLayoutParams(lp);
        }
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    private void initData(){
        mFragments = new ArrayList<>();

        for (int i =0;i<4;i++){
            CommonFragment fragment = new CommonFragment();
            fragment.bindData(IMAGE_ID[i],NAME[i],SUMMARY[i]);
            mFragments.add(fragment);

        }
        mAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageMargin(-40);
        mViewPager.setPageTransformer(false, new CustPagerTransformer(this));



    }



}
