package com.example.asus.recruit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.recruit.R;
import com.example.asus.recruit.widget.DragLayout;


import org.w3c.dom.Text;

import static com.example.asus.recruit.configs.Extra.CONTENT;
import static com.example.asus.recruit.configs.Extra.IMAGE_ID;
import static com.example.asus.recruit.configs.Extra.NAME;
import static com.example.asus.recruit.configs.Extra.SUMMARY;
import static com.example.asus.recruit.configs.Transtion.GROUPNAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.ICON_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.IMAGE_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.NAME_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.SUMMARY_TRANSITION_NAME;
import static com.example.asus.recruit.configs.Transtion.YANFA_TRANSITION_NAME;


public class CommonFragment extends Fragment implements DragLayout.GotoDetailListener,View.OnClickListener {
    private ImageView mImage;

    private TextView mTextGroupName;
    private TextView mTextName;
    private TextView mTextSummary;

    private String mName;
    private String mSummary;
    private String mContent;
    private int mImageId;
    private int mImageLongId;
    private DragLayout mDragLayout;
    private static final String TAG = "CommonFragment";





    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, null);
        initView(rootView);


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",mName);
        outState.putString("summary",mSummary);
        outState.putString("content",mContent);
        outState.putInt("id",mImageId);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState!=null){
            mImageId = savedInstanceState.getInt("id");
            mImageLongId = savedInstanceState.getInt("longId");
            mName = savedInstanceState.getString("name");
            mSummary = savedInstanceState.getString("summary");
            mContent = savedInstanceState.getString("content");

            mImage.setImageResource(mImageId);
//            if (!mName.equals("Java 后台")){
//                Glide.with(getActivity()).load(mImageId).into(mImage);
//
//            }

            mTextName.setText(mName);
            mTextGroupName.setText(mName);
            mTextSummary.setText(mSummary);
        }
    }

    private void initView(View view) {
        mDragLayout = view.findViewById(R.id.drag_layout);
        mImage = (ImageView) mDragLayout.findViewById(R.id.image);
        mTextName = mDragLayout.findViewById(R.id.tv_name);
        mTextSummary = mDragLayout.findViewById(R.id.tv_summary);
        mTextGroupName = mDragLayout.findViewById(R.id.tv_group_name);


            mImage.setImageResource(mImageId);
//        if (!mName.equals("Java 后台")){
//            Glide.with(getActivity()).load(mImageId).into(mImage);
//
//        }



        mTextName.setText(mName);
        mTextGroupName.setText(mName);
        mTextSummary.setText(mSummary);
        mDragLayout.setTextViewAnimation(mTextGroupName);

        mDragLayout.setGotoDetailListener(this);
        mTextSummary.setOnClickListener(this);
        mTextName.setOnClickListener(this);

    }

    @Override
    public void gotoDetail() {
        Activity activity = (Activity) getContext();
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair(mImage, IMAGE_TRANSITION_NAME),
                new Pair(mTextName, NAME_TRANSITION_NAME),
                new Pair(mTextSummary, SUMMARY_TRANSITION_NAME))

                ;


        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(IMAGE_ID, mImageId);
        intent.putExtra(NAME, mName);
        intent.putExtra(SUMMARY, mSummary);
        intent.putExtra(CONTENT,mContent);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public void bindData(int imageId,int imageLongId, String name, String sum,String content) {
       this.mImageId = imageId;
       this.mImageLongId = imageLongId;
       this.mName = name;
       this.mSummary = sum;
       this.mContent = content;



    }

    @Override
    public void onClick(View view) {
        Activity activity = (Activity) getContext();
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair(mImage, IMAGE_TRANSITION_NAME),
                new Pair(mTextGroupName, GROUPNAME_TRANSITION_NAME),
                new Pair(mTextName, NAME_TRANSITION_NAME),
                new Pair(mTextSummary, SUMMARY_TRANSITION_NAME))

               ;


        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(IMAGE_ID, mImageId);
        intent.putExtra(NAME, mName);
        intent.putExtra(SUMMARY, mSummary);
        intent.putExtra(CONTENT,mContent);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
