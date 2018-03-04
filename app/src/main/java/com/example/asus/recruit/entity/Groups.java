package com.example.asus.recruit.entity;



public class Groups {
    private String mName;
    private int mImageId;
    private String mSummary;
    private String mIntroducion;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIntroducion() {
        return mIntroducion;
    }

    public void setIntroducion(String introducion) {
        mIntroducion = introducion;
    }
}
