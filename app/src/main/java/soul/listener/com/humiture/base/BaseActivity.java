package soul.listener.com.humiture.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.manager.AppManager;
import soul.listener.com.humiture.a_views.IBaseView;
import soul.listener.com.humiture.customcontrol.CustomProgressDialog;

/**
 * @description IBasePresenter
 * Created by kuan on 2017/7/31.
 */

public abstract class BaseActivity extends Activity implements IBaseView {
    public Activity mActivity;
    private CustomProgressDialog mDialog;
    private Unbinder mUnbinder;
    public static final String EXTRA = "extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayouID());
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getApplicationContext().getResources().getColor(R.color.deepblue));
        }

        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        mDialog = new CustomProgressDialog(this);

        AppManager.getAppManager().addActivity(this);
        initView();

        resetFragment(savedInstanceState);
        initData();
        initEvent();
    }

    protected abstract int getLayouID() ;
    protected abstract void initView();

    protected void resetFragment(Bundle savedInstanceState) {
    }
    protected void initData() {
    }
    protected void initEvent() {
    }

    public void startActivity(Class<?> cls) {
        Intent it = new Intent(this, cls);
        this.startActivity(it);
    }

    public void startActivityForResult(Class<?> cls, int reqCode) {
        Intent it = new Intent(this, cls);
        this.startActivityForResult(it, reqCode);
    }

    public void startActivityWithExtra(Class<?> cls, String data) {
        Intent it = new Intent(this, cls);
        it.putExtra(EXTRA, data);
        this.startActivity(it);
    }

    public void startActivityWithExtras(Class<?> cls, Bundle bundle) {
        Intent it = new Intent(this, cls);
        it.putExtras(bundle);
        this.startActivity(it);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        hideDialog();
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    @Override
    public void showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        mDialog.dismissDialog();
    }

}