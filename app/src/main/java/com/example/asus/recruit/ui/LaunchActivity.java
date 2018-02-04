package com.example.asus.recruit.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.asus.recruit.R;


public class LaunchActivity extends AppCompatActivity {

    private AlphaAnimation mAnimator;
    ImageView mIvBgBefore;

    private void initView() {
        this.mIvBgBefore = ((ImageView) findViewById(R.id.iv_bg_before));
        this.mAnimator = new AlphaAnimation(1.0F, 0.0F);
        this.mAnimator.setDuration(2500L);
        this.mAnimator.setFillAfter(true);
        this.mAnimator.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation paramAnonymousAnimation) {
                LaunchActivity.this.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.overridePendingTransition(R.anim.slide_x0, R.anim.slideto_left);
                LaunchActivity.this.finish();
            }

            public void onAnimationRepeat(Animation paramAnonymousAnimation) {
            }

            public void onAnimationStart(Animation paramAnonymousAnimation) {
            }
        });
        this.mIvBgBefore.startAnimation(this.mAnimator);
    }

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

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
    }
}


