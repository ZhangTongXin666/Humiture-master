package soul.listener.com.humiture.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import soul.listener.com.humiture.R;

/**
 * 密码编辑框
 * <p/>
 * Created by ysq on 20/9/16.
 */
public class PasswordEditText extends EditText {

    // 模式的显示图标
    @DrawableRes
    private int mShowPwdIcon = R.drawable.ic_visibility_24dp;

    // 模式的加密图标
    @DrawableRes
    private int mHidePwdIcon = R.drawable.ic_visibility_off_24dp;

    private boolean mIsShowPwdIcon; // 是否显示指示器

    private Drawable mDrawableSide; // 显示指示器

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFields();
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
            initFields();
    }

    // 初始化布局
    public void initFields() {
        // 密码状态
        setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        showPasswordVisibilityIndicator(false);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                   showPasswordVisibilityIndicator(true);
                } else {
                    mIsShowPwdIcon = false;
                    restorePasswordIconVisibility(mIsShowPwdIcon);
                    showPasswordVisibilityIndicator(false); // 隐藏指示器
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP){
            final int x = (int) event.getX(); // 点击的位置
            boolean isChange = x > (getWidth() - getTotalPaddingRight())
                    && x < (getWidth() - getPaddingRight());

            if (isChange) {
                togglePasswordIconVisibility(); // 变换状态
            }
        }
        return super.onTouchEvent(event);
    }


    // 显示密码提示标志
    private void showPasswordVisibilityIndicator(boolean shouldShowIcon) {
        if (shouldShowIcon) {
            Drawable drawable = mIsShowPwdIcon ?
                    ContextCompat.getDrawable(getContext(), mHidePwdIcon) :
                    ContextCompat.getDrawable(getContext(), mShowPwdIcon);
            // 在最右侧显示图像
            drawable.setBounds(0 ,0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            setCompoundDrawablesRelative(null, null, drawable, null);
            mDrawableSide = drawable;
        } else {
            // 不显示周边的图像
            setCompoundDrawablesRelative(null, null, null, null);
            mDrawableSide = null;
        }
    }

    // 变换状态
    private void togglePasswordIconVisibility() {
        mIsShowPwdIcon = !mIsShowPwdIcon;
        restorePasswordIconVisibility(mIsShowPwdIcon);
        showPasswordVisibilityIndicator(true);
    }

    // 设置密码指示器的状态
    private void restorePasswordIconVisibility(boolean isShowPwd) {
        if (isShowPwd) {
            // 可视密码输入
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            // 非可视密码状态
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
        // 移动光标
        setSelection(getText().length());
    }
}
