package soul.listener.com.humiture.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.WheatherModel;
import soul.listener.com.humiture.a_presenters.BasePresenter;
import soul.listener.com.humiture.a_presenters.WheatherPresenter;
import soul.listener.com.humiture.a_views.WheatherView;
import soul.listener.com.humiture.base.BaseMvpActivity;
import soul.listener.com.humiture.util.PermissionUtil;
import soul.listener.com.humiture.util.ToastUtil;


/**
 * Created by liuyue on 2017/12/3.
 */

public class WheatherActivity extends BaseMvpActivity<WheatherPresenter> implements WheatherView {

    @BindView(R.id.rl_background)
    RelativeLayout rl_background;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_now_wendu)
    TextView tv_now_wendu;
    @BindView(R.id.tv_now_type)
    TextView tv_now_type;
    @BindView(R.id.tv_now_quality)
    TextView tv_now_quality;
    @BindView(R.id.tv_now_qualitynumber)
    TextView tv_now_qualitynumber;
    @BindView(R.id.tv_today_fengxiang)
    TextView tv_today_fengxiang;
    @BindView(R.id.tv_today_fengli)
    TextView tv_today_fengli;
    @BindView(R.id.tv_today_humidity)
    TextView tv_today_humidity;
    @BindView(R.id.tv_today_sunrise)
    TextView tv_today_sunrise;
    @BindView(R.id.tv_today_sunset)
    TextView tv_today_sunset;
    @BindView(R.id.tv_day1_date)
    TextView tv_day1_date;
    @BindView(R.id.tv_day1_high)
    TextView tv_day1_high;
    @BindView(R.id.tv_day1_low)
    TextView tv_day1_low;
    @BindView(R.id.tv_day1_type1)
    TextView tv_day1_type1;
    @BindView(R.id.tv_day1_fengxaing1)
    TextView tv_day1_fengxaing1;
    @BindView(R.id.tv_day1_fengli1)
    TextView tv_day1_fengli1;
    @BindView(R.id.tv_day1_type2)
    TextView tv_day1_type2;
    @BindView(R.id.tv_day1_fengxiang2)
    TextView tv_day1_fengxiang2;
    @BindView(R.id.tv_day1_fengli2)
    TextView tv_day1_fengli2;
    @BindView(R.id.tv_day2_date)
    TextView tv_day2_date;
    @BindView(R.id.tv_day2_high)
    TextView tv_day2_high;
    @BindView(R.id.tv_day2_low)
    TextView tv_day2_low;
    @BindView(R.id.tv_day2_type1)
    TextView tv_day2_type1;
    @BindView(R.id.tv_day2_fengxiang1)
    TextView tv_day2_fengxiang1;
    @BindView(R.id.tv_day2_fengli1)
    TextView tv_day2_fengli1;
    @BindView(R.id.tv_day2_type2)
    TextView tv_day2_type2;
    @BindView(R.id.tv_day2_fengxiang2)
    TextView tv_day2_fengxiang2;
    @BindView(R.id.tv_day2_fengli2)
    TextView tv_day2_fengli2;
    @BindView(R.id.tv_day3_date)
    TextView tv_day3_date;
    @BindView(R.id.tv_day3_high)
    TextView tv_day3_high;
    @BindView(R.id.tv_day3_low)
    TextView tv_day3_low;
    @BindView(R.id.tv_day3_type1)
    TextView tv_day3_type1;
    @BindView(R.id.tv_day3_fengxaing1)
    TextView tv_day3_fengxaing1;
    @BindView(R.id.tv_day3_fengli1)
    TextView tv_day3_fengli1;
    @BindView(R.id.tv_day3_type2)
    TextView tv_day3_type2;
    @BindView(R.id.tv_day3_fengxiang2)
    TextView tv_day3_fengxiang2;
    @BindView(R.id.tv_day3_fengli2)
    TextView tv_day3_fengli2;
    @BindView(R.id.tv_day4_date)
    TextView tv_day4_date;
    @BindView(R.id.tv_day4_high)
    TextView tv_day4_high;
    @BindView(R.id.tv_day4_low)
    TextView tv_day4_low;
    @BindView(R.id.tv_day4_type1)
    TextView tv_day4_type1;
    @BindView(R.id.tv_day4_fengxaing1)
    TextView tv_day4_fengxaing1;
    @BindView(R.id.tv_day4_fengli1)
    TextView tv_day4_fengli1;
    @BindView(R.id.tv_day4_type2)
    TextView tv_day4_type2;
    @BindView(R.id.tv_day4_fengxiang2)
    TextView tv_day4_fengxiang2;
    @BindView(R.id.tv_day4_fengli2)
    TextView tv_day4_fengli2;
    @BindView(R.id.tv_infoHome)
    TextView tvInfoHome;

    @Override
    public WheatherPresenter createPresenter() {
        return (new WheatherPresenter(this));
    }

    @Override
    protected int getLayouID() {
        return R.layout.activity_wheather;
    }

    @Override
    protected void initView() {
        PermissionUtil.checkPromiss(this);
    }

    @Override
    protected void initData(){
        mPresenter.getWheather();
    }

    public void refreshBackground(boolean isNight, String type) {
        if (isNight == false && type.contains("晴")) {
            rl_background.setBackgroundResource(R.drawable.bg_day_sun);
        } else if (isNight == true && type.contains("晴")) {
            rl_background.setBackgroundResource(R.drawable.bg_night_sun);
        } else if (isNight == false && type.contains("多云")) {
            rl_background.setBackgroundResource(R.drawable.bg_day_yin);
        } else if (isNight == true && type.contains("多云")) {
            rl_background.setBackgroundResource(R.drawable.bg_night_yin);
        } else if (type.contains("雷")) {
            rl_background.setBackgroundResource(R.drawable.bg_day_lei);
        } else if (type.contains("小雨")) {
            if (isNight) {
                rl_background.setBackgroundResource(R.drawable.bg_night_smallrain);
            } else {
                rl_background.setBackgroundResource(R.drawable.bg_day_small_rain);
            }
        } else if (type.contains("雨")) {
            if (isNight) {
                rl_background.setBackgroundResource(R.drawable.bg_night_bigrain);
            } else {
                rl_background.setBackgroundResource(R.drawable.bg_day_bigrain);
            }
        } else if (type.contains("雾")) {
            rl_background.setBackgroundResource(R.drawable.bg_day_fog);
        } else if (type.contains("霾")) {
            rl_background.setBackgroundResource(R.drawable.bg_day_haze);
        } else if (type.contains("雪")) {
            if (isNight) {
                rl_background.setBackgroundResource(R.drawable.bg_night_snow);
            } else {
                rl_background.setBackgroundResource(R.drawable.bg_day_snow);
            }
        }

    }

    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tv_infoHome:
                startActivity(new Intent(this, HumitureActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
                default:break;
        }
    }

    @Override
    public void setDayDate(List<String> list) {
        tv_day1_date.setText(list.get(0));
        tv_day2_date.setText(list.get(1));
        tv_day3_date.setText(list.get(2));
        tv_day4_date.setText(list.get(3));

    }

    @Override
    public void setHigh(List<String> list) {
        tv_day1_high.setText(list.get(0));
        tv_day2_high.setText(list.get(1));
        tv_day3_high.setText(list.get(2));
        tv_day4_high.setText(list.get(3));
    }

    @Override
    public void setLow(List<String> list) {
        tv_day1_low.setText(list.get(0));
        tv_day2_low.setText(list.get(1));
        tv_day3_low.setText(list.get(2));
        tv_day4_low.setText(list.get(3));
    }

    @Override
    public void setDayType(List<String> list) {
        tv_day1_type1.setText(list.get(0));
        tv_day2_type1.setText(list.get(1));
        tv_day3_type1.setText(list.get(2));
        tv_day4_type1.setText(list.get(3));
    }

    @Override
    public void setDayfengxiang(List<String> list) {
        tv_day1_fengxaing1.setText(list.get(0));
        tv_day2_fengxiang1.setText(list.get(1));
        tv_day3_fengxaing1.setText(list.get(2));
        tv_day4_fengxaing1.setText(list.get(3));
    }

    @Override
    public void setDayfengli(List<String> list) {
        tv_day1_fengli1.setText(list.get(0));
        tv_day2_fengli1.setText(list.get(1));
        tv_day3_fengli1.setText(list.get(2));
        tv_day4_fengli1.setText(list.get(3));
    }

    @Override
    public void setNightType(List<String> list) {
        tv_day1_type2.setText(list.get(0));
        tv_day2_type2.setText(list.get(1));
        tv_day3_type2.setText(list.get(2));
        tv_day4_type2.setText(list.get(3));
    }

    @Override
    public void setNightfengxiang(List<String> list) {
        tv_day1_fengxiang2.setText(list.get(0));
        tv_day2_fengxiang2.setText(list.get(1));
        tv_day3_fengxiang2.setText(list.get(2));
        tv_day4_fengxiang2.setText(list.get(3));
    }

    @Override
    public void setNightfengli(List<String> list) {
        tv_day1_fengli2.setText(list.get(0));
        tv_day2_fengli2.setText(list.get(1));
        tv_day3_fengli2.setText(list.get(2));
        tv_day4_fengli2.setText(list.get(3));
    }

    @Override
    public void setNowQuality(String str) {
        tv_now_quality.setText(str);
    }

    @Override
    public void setNowQualityNumber(String str) {
        tv_now_qualitynumber.setText(str);
    }

    @Override
    public void setTodayFengXiang(String str) {
        tv_today_fengxiang.setText(str);
    }

    @Override
    public void setTodayFengLi(String str) {
            tv_today_fengli.setText(str);
    }

    @Override
    public void setTodayHumidity(String str) {
            tv_today_humidity.setText(str);
    }

    @Override
    public void setTodaySunrise(String str) {
        tv_today_sunrise.setText(str);
    }

    @Override
    public void setTodaySunset(String str) {
        tv_today_sunset.setText(str);
    }

    @Override
    public void setLocation(String str) {
        tv_location.setText(str);
    }

    @Override
    public void setWendu(String str) {
            tv_now_wendu.setText(str);
    }

    @Override
    public void setNowType(String str) {
        tv_now_type.setText(str);
    }

    @Override
    public void getWeatherFailure(String str) {
        hideDialog();
        ToastUtil.makeText(str);
        initData();
    }

    @Override
    public void getWeatherSuccess() {
        hideDialog();
        ToastUtil.makeText("获取天气成功");
    }
}
