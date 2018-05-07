package soul.listener.com.humiture.util;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.ArrayList;
import java.util.List;

import soul.listener.com.humiture.R;

/**
 * Created by kys_31 on 2017/12/15.
 */

public class ViewUtil {
    /**
     * 改变Dialog大小
     * @param dialog
     * @param context
     * @param width
     * @param height
     */
    public static void setDialogWindowAttr(Dialog dialog, int width, int height){
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = width;
        lp.height = height;
        dialog.getWindow().setAttributes(lp);
    }


}
