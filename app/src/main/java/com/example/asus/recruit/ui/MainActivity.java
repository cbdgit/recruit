package com.example.asus.recruit.ui;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;



import com.example.asus.recruit.R;
import com.example.asus.recruit.adapter.FragmentAdapter;
import com.example.asus.recruit.widget.CustPagerTransformer;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Build;

import android.support.v4.view.ViewPager;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.widget.Toast;


import static com.example.asus.recruit.configs.Content.CONTENT;
import static com.example.asus.recruit.configs.Content.IMAGE_ID;
import static com.example.asus.recruit.configs.Content.IMAGE_LONG_ID;
import static com.example.asus.recruit.configs.Content.NAME;
import static com.example.asus.recruit.configs.Content.SUMMARY;

public class MainActivity extends AppCompatActivity {

    private View mPositionView;
    private ViewPager mViewPager;
    private List<CommonFragment> mFragments;
    private Animation mAnimation;
    private FragmentAdapter mAdapter;
    private Button mButton;
    private Boolean mIsExit;
    private Button mButtonBack;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mIsExit = false;
        }
    };
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();


    }


    private void initView() {

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


        mViewPager =  findViewById(R.id.viewpager);
        mPositionView = findViewById(R.id.position_view);
        mButton = findViewById(R.id.iv_baoming);
        mButtonBack = findViewById(R.id.iv_baoming_back);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecruitActivity.class);
                intent.putExtra("card",mViewPager.getCurrentItem());
                startActivity(intent);


            }
        });



        mAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.breath );


        mButtonBack.startAnimation(mAnimation);



        setImageViewchange();
        dealStatusBar();

    }


    /**
     * 改变ImageView 的颜色
     */
    private void setImageViewchange() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            GradientDrawable mBackground = (GradientDrawable) mButton.getBackground();
            GradientDrawable mGradientDrawable = (GradientDrawable)mButtonBack.getBackground();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ArgbEvaluator evaluator = new ArgbEvaluator(); // ARGB求值器
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                int evaluateback  = 0x00FFFFFF;
                int evaluate = 0x00FFFFFF; // 初始默认颜色（透明白）
                if (position == 0) {
                    evaluate = (Integer) evaluator.evaluate(positionOffset, 0XFF000001, 0XFFbc67fd);// 根据positionOffset和第0页~第1页的颜色转换范围取颜色值
                    evaluateback =(Integer)argbEvaluator.evaluate(positionOffset,0XAC000001,0XACBC67FD);
                } else if (position == 1) {

                    evaluate = (Integer) evaluator.evaluate(positionOffset, 0XFFbc67fd, 0XFF73e403); // 根据positionOffset和第1页~第2页的颜色转换范围取颜色值

                    evaluateback =(Integer)argbEvaluator.evaluate(positionOffset,0XACbc67fd,0XAC73e403);
                } else if (position == 2) {
                    evaluate = (Integer) evaluator.evaluate(positionOffset, 0XFF73e403
                            , 0XFFd3d2da); // 根据positionOffset和第2页~第3页的颜色转换范围取颜色值

                    evaluateback =(Integer)argbEvaluator.evaluate(positionOffset,0XAC73e403,0XACd3d2da);
                } else if (position==3){
                    evaluate = (Integer)evaluator.evaluate(positionOffset,0XFFd3d2da,0XFFfdfdfe);//

                    evaluateback =(Integer)argbEvaluator.evaluate(positionOffset,0XACd3d2da,0XACfdfdfe);
                }else {
                    evaluate = 0XFFfdfdfe;
                    evaluateback = 0XACfdfdfe;
                }

                mBackground.setColor(evaluate);
                mGradientDrawable.setColor(evaluateback);



            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!mIsExit) {
            mIsExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序!", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    private void initData() {
        mIsExit = false;
        mFragments = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CommonFragment fragment = new CommonFragment();
            fragment.bindData(IMAGE_ID[i],IMAGE_LONG_ID[i], NAME[i], SUMMARY[i], CONTENT[i]);
            mFragments.add(fragment);

        }
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageMargin(-50);
        mViewPager.setPageTransformer(false, new CustPagerTransformer(this));


    }


}
