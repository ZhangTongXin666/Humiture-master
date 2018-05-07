package soul.listener.com.humiture.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_presenters.LookBlockPresenter;
import soul.listener.com.humiture.a_views.LookBlockView;
import soul.listener.com.humiture.base.BaseMvpActivity;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.TimeUtil;

/**
 * Created by kys_31 on 2017/12/16.
 */

public class LookBlockActivity extends BaseMvpActivity<LookBlockPresenter> implements LookBlockView {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_blockLocation)
     TextView tvBlockLocation;
    @BindView(R.id.ll_blockLocation)
    LinearLayout llBlockLocation;
    @BindView(R.id.tv_block)
   public TextView tvBlock;
    @BindView(R.id.ll_block)
    LinearLayout llBlock;
    @BindView(R.id.lv_lineChat)
    public LineChart lvLineChat;
    @BindView(R.id.bt_upPage)
    Button btUpPage;
    @BindView(R.id.bt_nextPage)
    Button btNextPage;
    @BindView(R.id.tv_date)
    TextView tvDate;

    @Override
    public LookBlockPresenter createPresenter() {
        return new LookBlockPresenter(this);
    }

    @Override
    protected int getLayouID() {
        return R.layout.activity_lookblock;
    }

    @Override
    protected void initView() {
            /*设置状态栏颜色*/
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.text_black));
        setDate(TimeUtil.getSystemTime());
    }

    @Override
    public void initData(){
        mPresenter.initBlockData();

    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_blockLocation:
                mPresenter.showPop(Constants.SHOW_BLOCKLOCATION, tvBlockLocation);
                break;
            case R.id.ll_block:
                mPresenter.showPop(Constants.SHOW_BLOCK, tvBlock);
                break;
            case R.id.bt_nextPage:
                mPresenter.nextPage();
                break;
            case R.id.bt_upPage:
                mPresenter.upPage();
                break;
            case R.id.ll_date:
                TimeUtil.selectDate(this, tvDate);
                break;
            default:
                break;
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
    public void setBlockLocation(String content) {
        tvBlockLocation.setText(content);
    }

    @Override
    public void setBlock(String content) {
        tvBlock.setText(content);
    }

    @Override
    public void setDate(String content) {
        tvDate.setText(content);
    }

    @Override
    public String getBlockLocation() {
        return tvBlockLocation.getText().toString();
    }

    @Override
    public String getBlock() {
        return tvBlock.getText().toString();
    }

    @Override
    public String getDate() {
        return tvDate.getText().toString();
    }

}
