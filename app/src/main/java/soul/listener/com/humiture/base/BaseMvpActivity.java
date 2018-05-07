package soul.listener.com.humiture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import soul.listener.com.humiture.a_presenters.BasePresenter;


/**
 * @description mvpactivity基类
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
