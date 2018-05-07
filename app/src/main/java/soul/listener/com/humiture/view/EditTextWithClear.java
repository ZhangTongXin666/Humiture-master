package soul.listener.com.humiture.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import soul.listener.com.humiture.R;

public class EditTextWithClear extends EditText implements
        OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable; // 清除内容按钮
    private boolean hasFocus; // 是否聚焦

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EditTextWithClear(Context context) {
        super(context, null);
        initCustomEditText();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomEditText();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EditTextWithClear(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCustomEditText();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initCustomEditText() {

        mClearDrawable = getCompoundDrawablesRelative()[2]; // 获得右侧的图标
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.drawable.edittext_clear_logo);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);

    }


    /**
     * 根据是否显示来重新绘制清楚图标
     *
     * @param isShowClearIcon :清除图标是否显示
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setClearIconVisible(boolean isShowClearIcon) {
        Drawable locationRight = isShowClearIcon ? mClearDrawable : null;
        setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0],
                getCompoundDrawablesRelative()[1], locationRight,
                getCompoundDrawablesRelative()[3]);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) { // 内容改变之前调用(实际上内容并没有发生改变)
    }

    @Override
    public void afterTextChanged(Editable s) { // 在Edittext中的内容被改变之后就被调用
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore,
                              int lengthAfter) { // text是原有的文本
        // 从start开始的lengthAfter个字符替换旧的长度为lengthBefore的旧文本

        if (hasFocus) {
            setClearIconVisible(text.length() > 0);

        } else {
            setClearIconVisible(false);
        }
    }

    /**
     * getTotalPaddingRight() : 清除小图标左边缘到 控件有边缘的距离 getPaddingRight() :
     * 清楚小图标右边缘到控件右边缘之间的距离
     */
    @SuppressWarnings("unused")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mClearDrawable != null
                && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            boolean horizontalWidth = x > (this.getWidth() - getTotalPaddingRight())
                    && x < (this.getWidth() - getPaddingRight());

            if (horizontalWidth) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

}
