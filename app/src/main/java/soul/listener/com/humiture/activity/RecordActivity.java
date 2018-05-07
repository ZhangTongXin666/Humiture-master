package soul.listener.com.humiture.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soul.listener.com.humiture.Adapter.RecordAdapter;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.a_presenters.RecordPresenter;
import soul.listener.com.humiture.a_views.RecordView;
import soul.listener.com.humiture.base.BaseMvpActivity;
import soul.listener.com.humiture.util.ToastUtil;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class RecordActivity extends BaseMvpActivity<RecordPresenter> implements RecordView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.rv_view)
    RecyclerView rvView;

    public static final String ACTION = "action";
    public static final String LOOKONE = "lookOne";
    public static final String LOOKALL = "lookAll";
    @BindView(R.id.tv_noData)
    TextView tvNoData;
    private RecordAdapter recordAdapter;

    @Override
    public RecordPresenter createPresenter() {
        return new RecordPresenter(this);
    }

    @Override
    protected int getLayouID() {
        return R.layout.activity_record;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.getTemperatureData();
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
            case R.id.iv_filter:
                mPresenter.showFilterData();
                break;
            default:
                break;
        }
    }


    @Override
    public void showView(List<RecordModel> list) {
        if (list.size() != 0) {
            rvView.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            recordAdapter = new RecordAdapter(this, list);
            rvView.setLayoutManager(new LinearLayoutManager(this));
            rvView.setAdapter(recordAdapter);
        } else {
            rvView.setAdapter(null);
            rvView.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    public static Intent getExtraIntent(String extraValue, Activity activity) {
        Intent intent = new Intent(activity, RecordActivity.class);
        intent.putExtra(ACTION, extraValue);
        return intent;
    }
}
