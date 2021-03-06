package com.example.asus.recruit.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.asus.recruit.R;


public class LaunchActivity extends AppCompatActivity {

    private AlphaAnimation mAnimator;
    private ImageView mImageBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_launch);
        initView();
    }


    private void initView() {
        mImageBefore = ((ImageView) findViewById(R.id.iv_bg_before));
        mAnimator = new AlphaAnimation(1.0F, 0.0F);
        mAnimator.setDuration(3000L);
        mAnimator.setFillAfter(true);
        mAnimator.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation paramAnonymousAnimation) {
                LaunchActivity.this.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.finish();
            }

            public void onAnimationRepeat(Animation paramAnonymousAnimation) {
            }

            public void onAnimationStart(Animation paramAnonymousAnimation) {
            }
        });
        mImageBefore.startAnimation(mAnimator);



    }


}


