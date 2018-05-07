package soul.listener.com.humiture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_presenters.LoginPresenter;
import soul.listener.com.humiture.a_views.LoginView;
import soul.listener.com.humiture.base.BaseMvpActivity;
import soul.listener.com.humiture.util.ToastUtil;
import soul.listener.com.humiture.view.EditTextWithClear;
import soul.listener.com.humiture.view.OwlView;
import soul.listener.com.humiture.view.PasswordEditText;


/**
 * Created by kys_31 on 2017/11/30.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.etUsername)
    EditTextWithClear etUsername;
    @BindView(R.id.etPassword)
    PasswordEditText etPassword;
    @BindView(R.id.btLogin)
    TextView btLogin;
    @BindView(R.id.owl_view)
    OwlView mOwlView;
    @BindView(R.id.input_layout_name)
    LinearLayout inputLayoutName;
    @BindView(R.id.id_line)
    View idLine;
    @BindView(R.id.input_layout_psw)
    LinearLayout inputLayoutPsw;
    @BindView(R.id.et_phoneNumber)
    EditTextWithClear etPhoneNumber;
    @BindView(R.id.input_layout_phoneNumber)
    LinearLayout inputLayoutPhoneNumber;
    @BindView(R.id.input_layout)
    LinearLayout inputLayout;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.tv_userLogin)
    TextView tvUserLogin;
    @BindView(R.id.tv_managerLogin)
    TextView tvManagerLogin;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayouID() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @OnFocusChange(R.id.etPassword)
    public void startAnim() {
        if (etPassword.hasFocus()) {
            mOwlView.open();
        } else {
            mOwlView.close();
        }
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailture(String failureMessage) {
        etPassword.setVisibility(View.VISIBLE);
        etUsername.setVisibility(View.VISIBLE);
        ToastUtil.makeTextSafe(failureMessage);
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @OnClick(R.id.btLogin)
    public void onLogin() {
        if (inputLayoutName.getVisibility() == View.VISIBLE)
        mPresenter.fastLogin(getUsername(), getPassword());
        else mPresenter.phoneNumberLogin(etPhoneNumber.getText().toString());
    }

    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tv_userLogin:
                inputLayoutName.setVisibility(View.GONE);
                inputLayoutPsw.setVisibility(View.GONE);
                idLine.setVisibility(View.GONE);
                inputLayoutPhoneNumber.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_managerLogin:
                inputLayoutName.setVisibility(View.VISIBLE);
                inputLayoutPsw.setVisibility(View.VISIBLE);
                idLine.setVisibility(View.VISIBLE);
                inputLayoutPhoneNumber.setVisibility(View.GONE);
                break;
                default:break;
        }
    }


}
