package soul.listener.com.humiture.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.ResidentModel;
import soul.listener.com.humiture.a_presenters.HumiturePresenter;
import soul.listener.com.humiture.a_views.HumitureView;
import soul.listener.com.humiture.base.BaseMvpActivity;
import soul.listener.com.humiture.util.TimeUtil;

/**
 * Created by kys_31 on 2017/12/5.
 */

public class HumitureActivity extends BaseMvpActivity<HumiturePresenter> implements HumitureView {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.lv_lineChat)
    public LineChart mDoubleLineChar;
    @BindView(R.id.bt_upPage)
    Button btUpPage;
    @BindView(R.id.bt_nextPage)
    Button btNextPage;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private boolean mBrClick = true;
    private ResidentModel mResidentModel;
    private ImageView mIvUpDay;
    private ImageView mIvNextDay;
    private TextView mTvDate;

    @Override
    public HumiturePresenter createPresenter() {
        return new HumiturePresenter(this);
    }

    @Override
    protected int getLayouID() {
        return R.layout.layout_fullscreen;
    }


    @Override
    protected void initView() {
         /*设置状态栏颜色*/
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.text_black));
        mIvNextDay = findViewById(R.id.iv_nextDay);
        mIvUpDay = findViewById(R.id.iv_upDay);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_rotate);
        mIvNextDay.startAnimation(animation);
        mTvDate = findViewById(R.id.tv_date);
        mTvDate.setText(TimeUtil.getSystemTime());
    }

    @Override
    protected void initData(){
        mPresenter.initBlockData();
    }

    /**
     * 判断是否是用户通过手机号登录的
     */
    public void userLogin(){
        Intent intent = getIntent();
        mResidentModel = (ResidentModel) intent.getSerializableExtra("userMessage");
        if (mResidentModel != null){
            mBrClick = false;
            mPresenter.showOnlyUser(mResidentModel);
            return;
        }
        mPresenter.showFilterDialog(mBrClick);
    }

    @Override
    public void setDate(String date) {
        Log.e("TAG", "setDate: "+date);
        mTvDate.setText(date);
    }

    @Override
    public String getDate() {
        return mTvDate.getText().toString();
    }

    @Override
    public void setAddress(String str) {
        tvAddress.setText(str);
    }
    @Override
    public String getAddress(){
        return tvAddress.getText().toString();
    }

    @Override
    public void setNextClick(boolean click) {
        btNextPage.setEnabled(click);
        if (!click){
            btNextPage.setText("尾页");
            btNextPage.setTextColor(Color.GRAY);
        }else {
            btNextPage.setText("下一页");
            btNextPage.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void setUpClick(boolean click) {
        btUpPage.setEnabled(click);
        if (!click){
            btUpPage.setText("首页");
            btUpPage.setTextColor(Color.GRAY);
        }else {
            btUpPage.setText("上一页");
            btUpPage.setTextColor(Color.WHITE);
        }
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_filter:
                if (mBrClick){
                    mPresenter.showFilterDialog( mBrClick);
                }else {
                    mPresenter.showOnlyUser(mResidentModel);
                }
                break;
            case R.id.bt_nextPage:
                mPresenter.nextPage();
                break;
            case R.id.bt_upPage:
                mPresenter.upPage();
                break;
            case R.id.iv_nextDay:
                mPresenter.lookNextDay();
                break;
            case R.id.iv_upDay:
                mPresenter.lookUpDay();
                break;
            default:break;
        }
    }

}
