package com.example.asus.recruit.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.recruit.R;
import com.example.asus.recruit.contract.RecruitContract;
import com.example.asus.recruit.entity.HTTPResult;
import com.example.asus.recruit.entity.ValidateResult;
import com.example.asus.recruit.presenter.RecruitPresenter;
import com.example.asus.recruit.widget.CustomNestRadioGroup;
import com.example.asus.recruit.widget.OneKeyClearEditText;
import com.example.asus.recruit.widget.RegExpValidatorUtils;
import com.geetest.gt3unbindsdk.Bind.GT3GeetestBindListener;
import com.geetest.gt3unbindsdk.Bind.GT3GeetestUtilsBind;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


//public class RecruitActivity extends AppCompatActivity implements View.OnClickListener, RecruitContract.IRecruitView, View.OnTouchListener{
public class RecruitActivity extends AppCompatActivity implements View.OnClickListener, RecruitContract.IRecruitView {


    private String TAG = "RecruitActivity";
    @BindView(R.id.imageView_back) ImageView imageView_back;
    @BindView(R.id.editText_name) EditText editText_name;
    @BindView(R.id.editText_studentId) OneKeyClearEditText editText_studentId;
    @BindView(R.id.editText_college) EditText editText_college;
    @BindView(R.id.editText_class) EditText editText_class;
    @BindView(R.id.editText_duty) EditText editText_duty;
    @BindView(R.id.editText_email) EditText editText_email;
    @BindView(R.id.editText_qq) EditText editText_qq;
    @BindView(R.id.editText_telephone) EditText editText_telephone;
    @BindView(R.id.editText_skill) EditText editText_skill;
    @BindView(R.id.editText_introduce) EditText editText_introduce;
    @BindView(R.id.editText_wish) EditText editText_wish;
    @BindView(R.id.spinner_gender) Spinner spinner_gender;
    @BindView(R.id.radioButton_web) RadioButton radioButton_web;
    @BindView(R.id.radioButton_java) RadioButton radioButton_java;
    @BindView(R.id.radioButton_android) RadioButton radioButton_android;
    @BindView(R.id.radioButton_bigData) RadioButton radioButton_bigData;
    @BindView(R.id.button_confirm) Button button_confirm;
    @BindView(R.id.relativeLayout_recruitActivity) RelativeLayout relativeLayout_recruitActivity;
    @BindView(R.id.linearLayout_confirm) LinearLayout linearLayout_confirm;
    @BindView(R.id.scrollView3) ScrollView scrollView;
    private CustomNestRadioGroup radioGroup_direction;
    private Drawable oldBackground = null;
    private Drawable bgDrawable;

    private static final String mBaseUrl = "http://120.78.74.103/ready?t=";
    private String time;
    //private static final String validateURL = "http://120.78.74.103/robot/account/loginAccount";
    //private static final String validateURL = "";
    private static final String validateURL = null;
    private GT3GeetestUtilsBind gt3GeetestUtils;
    private RecruitPresenter recruitPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //失效
        requestWindowFeature(1);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_recruit);
        ButterKnife.bind(this);
        initView();
        //initValidate();
        int currentItem = getIntent().getIntExtra("card",0);
        switch (currentItem){
            case 0:
                Log.e(TAG, "onCreate: "+"后台");
                radioButton_java.setChecked(true);
                break;
            case 1:
                Log.e(TAG, "onCreate: "+"前端");
                radioButton_web.setChecked(true);
                break;
            case 2:
                Log.e(TAG, "onCreate: "+"安卓");
                radioButton_android.setChecked(true);
                break;
            case 3:
                Log.e(TAG, "onCreate: "+"大数据");
                radioButton_bigData.setChecked(true);
                break;
            case  4:
                Log.e(TAG, "onCreate: "+"研发");
                break;
            default:
                break;
        }
        recruitPresenter = new RecruitPresenter(this);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        imageView_back.setOnClickListener(this);
        button_confirm.setOnClickListener(this);
        radioGroup_direction = findViewById(R.id.radioGroup_direction);
//        changeColor(Color.parseColor("#66c3fe"));

//        bgDrawable = getResources().getColor(R.color.web)
        bgDrawable = new ColorDrawable(getResources().getColor(R.color.colorJava));

//        radioGroup_direction.setOnCheckedChangeListener(new CustomNestRadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CustomNestRadioGroup group, int checkedId) {
//                if (checkedId == R.id.radioButton_web) {
//                    Log.d("RecruitActivity", "web");
//                    changeColor(Color.parseColor("#bc67fd"));
//                    linearLayout_confirm.setBackgroundColor(getResources().getColor(R.color.colorWeb));
//                }else if (checkedId == R.id.radioButton_java) {
//                    Log.d("RecruitActivity", "java");
//                    changeColor(Color.parseColor("#000001"));
//                    linearLayout_confirm.setBackgroundColor(getResources().getColor(R.color.colorJava));
//                }else if (checkedId == R.id.radioButton_android) {
//                    Log.d("RecruitActivity", "android");
//                    changeColor(Color.parseColor("#73e403"));
//                    linearLayout_confirm.setBackgroundColor(getResources().getColor(R.color.colorAndroid));
//                }else if (checkedId == R.id.radioButton_bigData) {
//                    Log.d("RecruitActivity", "bigData");
//                    changeColor(Color.parseColor("#d3d2da"));
//                    linearLayout_confirm.setBackgroundColor(getResources().getColor(R.color.colorBigData));
//                }
//            }
//        });
    }

    private void initValidate() {

        long t;
        t = new Date().getTime();
        time = String.valueOf(t);

        recruitPresenter.getValidate(time);

        gt3GeetestUtils = new GT3GeetestUtilsBind(RecruitActivity.this);

        gt3GeetestUtils.getGeetest(RecruitActivity.this, mBaseUrl + time, validateURL, null, new GT3GeetestBindListener() {
            @Override
            public void gt3CloseDialog(int  num) {
                Log.d(TAG, "gt3CloseDialog");
            }

            @Override
            public void gt3DialogReady() {
                Log.d(TAG, "gt3DialogReady");
            }

            @Override
            public void gt3FirstResult(JSONObject jsonObject) {
                try {
                    Log.d(TAG, "success:" + jsonObject.getString("success") + ",challenge:"
                            + jsonObject.getString("challenge") + ",gt:"
                            + jsonObject.getString("gt"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Map<String, String> gt3CaptchaApi1() {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }

            @Override
            public boolean gt3SetIsCustom() {
//                return false;
                return true;
            }

            @Override
            public void gt3GetDialogResult(String result) {
            }

            @Override
            public void gt3GetDialogResult(boolean status, String result) {

                if (status) {
                    try {
                        JSONObject res_json = new JSONObject(result);
                        Log.d(TAG, "challenge:" + res_json.getString("geetest_challenge")
                                + "  validate:" + res_json.getString("geetest_validate")
                                + "  seccode:" + res_json.getString("geetest_seccode"));

                        gt3GeetestUtils.gt3TestFinish();

                        Log.d(TAG, res_json.getString("geetest_challenge") + res_json.getString("geetest_validate") +
                                res_json.getString("geetest_seccode") + editText_name.getText().toString() + editText_studentId.getText().toString() +
                                spinner_gender.getSelectedItem().toString() + editText_college.getText().toString() + editText_class.getText().toString() +
                                editText_duty.getText().toString() + editText_telephone.getText().toString() + "0" + editText_email.getText().toString() +
                                editText_qq.getText().toString() + ((RadioButton)findViewById(radioGroup_direction.getCheckedRadioButtonId())).getText().toString() +
                                editText_skill.getText().toString() + editText_introduce.getText().toString() + editText_wish.getText().toString());

                        recruitPresenter.commit(res_json.getString("geetest_challenge"), res_json.getString("geetest_validate"),
                                res_json.getString("geetest_seccode"), editText_name.getText().toString(), editText_studentId.getText().toString(),
                                spinner_gender.getSelectedItem().toString(), editText_college.getText().toString() + editText_class.getText().toString(), editText_duty.getText().toString(),
                                editText_telephone.getText().toString(), "0", editText_email.getText().toString(),
                                editText_qq.getText().toString(), ((RadioButton)findViewById(radioGroup_direction.getCheckedRadioButtonId())).getText().toString(),
                                editText_skill.getText().toString(), editText_introduce.getText().toString(), editText_wish.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                        gt3GeetestUtils.gt3TestClose();
                    }
                }
            }

            @Override
            public void gt3GeetestStatisticsJson(JSONObject jsonObject) {
            }

            @Override
            public Map<String, String> gt3SecondResult() {
                Map<String, String> map = new HashMap<String, String>();
                //map.put("testkey","12315");
                //map.put("name", "aaa");
                //map.put("password", "123");
                return map;
            }

            @Override
            public void gt3DialogSuccessResult(String result) {
                Log.d(TAG, "2" + result);                           //
                if(!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject jobj = new JSONObject(result);
                        String sta = jobj.getString("result");
                        if ("success".equals(sta)) {
                            gt3GeetestUtils.gt3TestFinish();

                        } else {
                            gt3GeetestUtils.gt3TestClose();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else
                {
                    gt3GeetestUtils.gt3TestClose();
                }
            }

            @Override
            public void gt3DialogOnError(String error) {    //...
                Log.i("dsd","gt3DialogOnError");
                gt3GeetestUtils.gt3TestFinish();
                recruitPresenter.withoutValidate(editText_name.getText().toString(), editText_studentId.getText().toString(),
                        spinner_gender.getSelectedItem().toString(), editText_college.getText().toString() + editText_class.getText().toString(), editText_duty.getText().toString(),
                        editText_telephone.getText().toString(), "0", editText_email.getText().toString(),
                        editText_qq.getText().toString(), ((RadioButton)findViewById(radioGroup_direction.getCheckedRadioButtonId())).getText().toString(),
                        editText_skill.getText().toString(), editText_introduce.getText().toString(), editText_wish.getText().toString());
            }
        });
        //设置是否可以点击屏幕边缘关闭验证码
        gt3GeetestUtils.setDialogTouch(true);
    }

    private void changeColor(int newColor) {
        Drawable colorDrawable = new ColorDrawable(newColor);
        //图层叠加
        LayerDrawable ld = new LayerDrawable(new Drawable[]{bgDrawable, colorDrawable});
        if(oldBackground == null) {
//            relativeLayout_recruitActivity.setBackgroundDrawable(ld);
            relativeLayout_recruitActivity.setBackgroundDrawable(ld);
        }else {
            //渐变切换
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{oldBackground, ld});
            relativeLayout_recruitActivity.setBackgroundDrawable(td);
            td.startTransition(600);
        }
        oldBackground = ld;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_back:
                finish();
                break;
            case R.id.button_confirm:
                if (TextUtils.isEmpty(editText_name.getText()) || TextUtils.isEmpty(editText_studentId.getText()) || TextUtils.isEmpty(editText_college.getText()) || TextUtils.isEmpty(editText_class.getText()) ||
                        TextUtils.isEmpty(editText_duty.getText()) || TextUtils.isEmpty(editText_telephone.getText()) || TextUtils.isEmpty(editText_email.getText()) ||
                        TextUtils.isEmpty(editText_qq.getText()) || TextUtils.isEmpty(editText_skill.getText()) || TextUtils.isEmpty(editText_introduce.getText()) ||
                        TextUtils.isEmpty(editText_wish.getText())) {
                    Toast.makeText(this, "请输入完整的内容 ！" ,Toast.LENGTH_SHORT).show();
                }else {
                    if (!RegExpValidatorUtils.isName(editText_name.getText().toString())) {
                        Toast.makeText(this, "名字一栏不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isStuNum(editText_studentId.getText().toString())) {
                        Toast.makeText(this, "学号错误了喔，仅限大一同学报名（特殊情况请联系师兄师姐）" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isClass(editText_college.getText().toString() + editText_class.getText().toString())) {
                        Toast.makeText(this, "学院专业班级不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isSelf(editText_duty.getText().toString())) {
                        Toast.makeText(this, "职务一栏不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isPhone(editText_telephone.getText().toString())) {
                        Toast.makeText(this, "再检查一下手机号码输正确了没有？" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isEmail(editText_email.getText().toString())) {
                        Toast.makeText(this, "再检查一下邮箱的格式？" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isQQ(editText_qq.getText().toString())) {
                        Toast.makeText(this, "再检查一下QQ的输正确了没有？" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isSelf(editText_skill.getText().toString())) {
                        Toast.makeText(this, "特长一栏不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isSelf(editText_introduce.getText().toString())) {
                        Toast.makeText(this, "自我介绍一栏不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else if (!RegExpValidatorUtils.isSelf(editText_wish.getText().toString())) {
                        Toast.makeText(this, "期望一栏不能有空格喔" ,Toast.LENGTH_SHORT).show();
                    }else {
                        initValidate();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(HTTPResult demandResult) {
        Toast.makeText(this, "报名成功 ！我们期待你的表现哦！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String errMsg) {
        Toast.makeText(this, "报名失败 !" + errMsg , Toast.LENGTH_SHORT).show();
    }

}