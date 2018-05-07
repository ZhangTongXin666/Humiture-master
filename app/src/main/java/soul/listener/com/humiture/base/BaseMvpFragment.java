package soul.listener.com.humiture.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soul.listener.com.humiture.a_presenters.BasePresenter;

/**
 * Created by kys_31 on 2017/11/30.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment{
    protected T presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        presenter = createPresenter();
        return super.onCreateView(inflater, group, bundle);
    }

    public abstract T createPresenter();

    @Override
    public void onDestroy(){
        if (presenter != null){
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }

}
