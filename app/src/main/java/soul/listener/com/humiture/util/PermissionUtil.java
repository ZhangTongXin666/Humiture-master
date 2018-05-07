package soul.listener.com.humiture.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * Created by kys_31 on 2017/12/6.
 */

public class PermissionUtil {

    /*申请获取位置权限*/
    public static void checkPromiss(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission1 = Manifest.permission.ACCESS_COARSE_LOCATION;
            String permission2 = Manifest.permission.ACCESS_FINE_LOCATION;
            activity.requestPermissions(new String[]{permission1, permission2}, 123);
        }
    }
}
