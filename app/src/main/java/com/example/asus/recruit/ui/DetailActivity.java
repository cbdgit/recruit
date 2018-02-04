package com.example.asus.recruit.ui;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.asus.recruit.R;


import static com.example.asus.recruit.configs.Content.CONTENT;
import static com.example.asus.recruit.configs.Extra.IMAGE_ID;
import static com.example.asus.recruit.configs.Extra.NAME;
import static com.example.asus.recruit.configs.Extra.SUMMARY;
import static com.example.asus.recruit.configs.Transtion.GROUPNAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.ICON_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.IMAGE_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.NAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.SUMMARY_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.YANFA_TRANSITION_NAME;

public class DetailActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextYanFa;
    private TextView mTextGroupName;
    private TextView mTextName;
    private TextView mTextSummary;
    private ImageView mImageIcon;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        intData();



    }


    private void initView(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        mImageIcon = findViewById(R.id.iv_icon);
        mImageView = findViewById(R.id.image);
        mTextYanFa = findViewById(R.id.tv_yanfa);
        mTextName = findViewById(R.id.tv_name);
        mTextGroupName = findViewById(R.id.tv_group_name);
        mTextSummary = findViewById(R.id.tv_summary);

        ViewCompat.setTransitionName(mImageView,IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(mImageIcon,ICON_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextGroupName,GROUPNAME_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextName, NAME_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextYanFa, YANFA_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextSummary,SUMMARY_TRANSITION_NAME);


    }

    private void intData(){
        int id = getIntent().getIntExtra(IMAGE_ID
                ,0);
        String name  =getIntent().getStringExtra(NAME);
        String summmary = getIntent().getStringExtra(SUMMARY);

        mImageView.setImageResource(id);
        mTextName.setText(name);
        mTextGroupName.setText(name);
        mTextSummary.setText(CONTENT[0]);
    }




}
