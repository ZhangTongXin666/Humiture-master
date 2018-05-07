package soul.listener.com.humiture.customcontrol;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import soul.listener.com.humiture.R;


public class CustomProgressDialog {

    private Context mContext;
    private Dialog mDialog;

    public CustomProgressDialog(Context context) {
        mContext = context;
    }

    public Dialog showDialog() {
        showDialog(null);
        return mDialog;
    }


    public Dialog showDialog(DialogInterface.OnCancelListener listener) {
        initDialog(listener);
        return mDialog;
    }

    private void initDialog(DialogInterface.OnCancelListener listener) {
        mDialog = new Dialog(mContext, R.style.Loding_Progress_Style);//Dialog样式
        /*dialog视图*/
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.progress_dialog_loading, null);
        TextView tv = (TextView) view.findViewById(R.id.progess_textview);
        ImageView iv = (ImageView) view.findViewById(R.id.progess_image);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.dialog_loading_anim);
        iv.startAnimation(animation);
        tv.setText("正在请求");
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 这里可以设置dialog的大小，也可以设置dialog title等
        // LayoutParams layoutParams = new LayoutParams(width * 80 / 100, 50);
        // mDialog.setContentView(viewDialog, layoutParams);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        if (mDialog != null) {
            mDialog.show();
        }
        mDialog.setCancelable(true);
        if (listener != null) {
            mDialog.setOnCancelListener(listener);
        }

    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }
}
