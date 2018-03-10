package com.example.asus.recruit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;

import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.recruit.R;
import com.example.asus.recruit.configs.Extra;


import static com.example.asus.recruit.configs.Extra.IMAGE_ID;
import static com.example.asus.recruit.configs.Extra.NAME;
import static com.example.asus.recruit.configs.Extra.SUMMARY;
import static com.example.asus.recruit.configs.Transtion.GROUPNAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.IMAGE_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.NAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.SUMMARY_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.YANFA_TRANSITION_NAME;

public class DetailActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mTextName;
    private TextView mTextSummary;




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



        mImage = findViewById(R.id.image);
        mTextName = findViewById(R.id.tv_name);
        mTextSummary = findViewById(R.id.tv_summary);

        ViewCompat.setTransitionName(mImage,IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextName, NAME_TRANSITION_NAME);
        ViewCompat.setTransitionName(mTextSummary,SUMMARY_TRANSITION_NAME);


    }

    private void intData(){
        int id = getIntent().getIntExtra(IMAGE_ID
                ,0);
        String name  =getIntent().getStringExtra(NAME);
        String content = getIntent().getStringExtra(Extra.CONTENT);

        Glide.with(this).load(id).into(mImage);
        mTextName.setText(name);
        mTextSummary.setText(content);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            DetailActivity.this.overridePendingTransition(R.anim.slideto_left, R.anim.slide_right);
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);

        }

    }



}
