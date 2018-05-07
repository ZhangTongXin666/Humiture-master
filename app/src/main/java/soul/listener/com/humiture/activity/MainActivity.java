package soul.listener.com.humiture.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import soul.listener.com.humiture.R;
import soul.listener.com.humiture.base.BaseActivity;
import soul.listener.com.humiture.db.SQLCursor;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayouID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.ll_user:
                startActivity(new Intent(this, HumitureActivity.class));
                break;
            case R.id.ll_block:
                startActivity(new Intent(this, LookBlockActivity.class));
                break;
            case R.id.ll_heatingtime_user:
                startActivity(new Intent(this,HeatingTimeActivity.class));
                break;
            case R.id.bt_record:
                startActivity(RecordActivity.getExtraIntent(RecordActivity.LOOKALL, this));
                break;
            case R.id.bt_outHome:
                startActivity(new Intent(this, WheatherActivity.class));
                break;
            case R.id.iv_back:
                finish();
                default:break;
        }
    }
}
