package soul.listener.com.humiture.util;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by kys_31 on 2017/12/2.
 */

public class SizeUtil {

    private static float mDensity = MyApplication.getContext().getResources().getDisplayMetrics().density;

    public static int getViewWidth(View view){
        return view.getWidth();
    }

    public static int getViewHeight(View view){
        return view.getHeight();
    }

    public static int getWindowWidth(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getWindowHeight(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

}
