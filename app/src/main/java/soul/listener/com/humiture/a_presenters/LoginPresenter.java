package soul.listener.com.humiture.a_presenters;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import soul.listener.com.humiture.a_model.PartDataSelectionModel;
import soul.listener.com.humiture.a_model.ResidentModel;
import soul.listener.com.humiture.a_model.SqlFactory;
import soul.listener.com.humiture.a_model.SqlInfoCallBack;
import soul.listener.com.humiture.a_model.SystemUserModel;
import soul.listener.com.humiture.activity.HumitureActivity;
import soul.listener.com.humiture.activity.LoginActivity;
import soul.listener.com.humiture.db.SQLCursor;
import soul.listener.com.humiture.db.SQLHelper;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.MD5Utils;
import soul.listener.com.humiture.util.SqlStateCode;
import soul.listener.com.humiture.util.StringUtils;
import soul.listener.com.humiture.util.ToastUtil;

/**
 * Created by kys_31 on 2017/11/30.
 */

public class LoginPresenter extends BasePresenter<LoginActivity>{

    private static final String TAG = "loginpresenter";
    public LoginPresenter(LoginActivity loginActivity) {
        super(loginActivity);
    }

    /**
     * 用户通过手机号登录
     * @param phoneNumber 手机号
     */
    public void phoneNumberLogin(final String phoneNumber){
        if (phoneNumber == null || phoneNumber.length() < 11){
            mView.loginFailture(Constants.PHONEFORMAT_NOSURE);
            return;
        }
        try {
            String[] selections = {"residentTel1", "residentTel2", "residentTel3"};
            String[] hazyOrExacts = {"=", "=", "="};
            String[] conditions = {phoneNumber, phoneNumber, phoneNumber};
            String[] parts = {"*"};
            SQLCursor.getPartDataBySelection(Constants.RESIDENT_TABLE_NO, parts, selections, hazyOrExacts, conditions, 0, 0, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    Intent intent = new Intent(mView, HumitureActivity.class);
                    intent.putExtra("userMessage", ((ResidentModel)sqlFactories.get(0)));
                    mView.startActivity(intent);
                    mView.finish();
                }

                @Override
                public void Faild(int num) {
                    if (num == SqlStateCode.STSTE_NOINTERFACE){
                        mView.loginFailture(SqlStateCode.getSqlFaildInfo(num));
                    }else {
                        mView.loginFailture(SqlStateCode.getSqlFaildInfo(SqlStateCode.STSTE_PHONENO));
                    }
                }
            }, false);
        }catch (SQLException e){
            ToastUtil.makeTextSafe(e.getMessage());
        }
    }

    /**
     * 管理员通过账号密码登录
     * @param userName 用户名
     * @param password 密码
     */
    public void fastLogin(String userID, String password){
        if (checkUserMessage(userID, password)){
            try {
                String md5Password = MD5Utils.getMD5(password);
                PartDataSelectionModel model = new PartDataSelectionModel();
                String[] selections = {Constants.SYSUSER_USERID, Constants.SYSUSER_PASSWORD};
                String[] hazyOrExacts = {"=", "="};
                String[] conditions = {userID, md5Password};
                String[] parts = {"*"};
                model.setSelections(selections);
                model.setHazyOrExact(hazyOrExacts);
                model.setConditions(conditions);
                model.setParts(parts);
                model.setTableNameNo(Constants.SYSUSER_TABLE_NO);
                SQLCursor.getPartDataBySelection(mView, model, new SqlInfoCallBack() {
                    @Override
                    public void Success(ArrayList<SqlFactory> sqlFactories) {
                        mView.loginSuccess();
                    }
                    @Override
                    public void Faild(int num) {
                        if (num == SqlStateCode.STSTE_NOINTERFACE){
                            mView.loginFailture(SqlStateCode.getSqlFaildInfo(num));
                        }else {
                            mView.loginFailture("用户名和密码不匹配");
                        }
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*检查用户名和密码填写格式*/
    private boolean checkUserMessage(String username, String password) {
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            mView.loginFailture(StringUtils.getFailureMessage(Constants.error_allEmpty));
            return false;
        }else if (TextUtils.isEmpty(username)){
            mView.loginFailture(StringUtils.getFailureMessage(Constants.error_usernameEmpty));
            return false;
        }else if (TextUtils.isEmpty(password)) {
            mView.loginFailture(StringUtils.getFailureMessage(Constants.error_passwordEmpty));
            return false;
        }
        return true;
    }

}
