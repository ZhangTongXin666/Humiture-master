package soul.listener.com.humiture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soul.listener.com.humiture.a_views.IBaseView;
import soul.listener.com.humiture.customcontrol.CustomProgressDialog;

/**
 * @description BaseFragment
 * Created by kuan on 2017/7/31.
 */


public abstract class BaseFragment extends Fragment implements IBaseView{
    protected View mView;
    protected CustomProgressDialog pDialog;
    protected BaseFragment pFragment;
    protected Unbinder pUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setLayout(),null);
        pUnbinder = ButterKnife.bind(getActivity());
        pDialog = new CustomProgressDialog(getActivity());
        pFragment = this;
        initView();
        initData();
        initAction();
        return mView;
    }

    public abstract int setLayout();
    public abstract void initView();

    public void initData(){}
    public void initAction(){}

    @Override
    public void showDialog() {
        if (!pDialog.isShowing()){
            pDialog.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        if (pDialog.isShowing()){
            pDialog.dismissDialog();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        hideDialog();
        pUnbinder.unbind();
    }
}
