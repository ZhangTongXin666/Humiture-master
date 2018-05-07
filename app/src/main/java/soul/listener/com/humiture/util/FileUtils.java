package soul.listener.com.humiture.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 文件路径管理
 */
public class FileUtils {

    // 文件下载保存路径
    private static final String DOWNLOAD = "download";

    // 应用缓存数据路径
    private static final String CACHE = "cache";

    // 应用图片缓存路径
    private static final String ICON = "icon";

    // 拍照图片路径
    private static final String IMAGE = "image";

    //截图保存路径
    private static final String SCREEN = "screen";

    //日志保存路径
    private static final String LOG = "log";

    //缓存保存路径,主要用于缓存web
    private static final String WEBCACHE = "webcache";

    private static final String WELCOME_PIC = "welcome";

    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/red/";
    }

    public static String getDownloadDir(Context context) {
        return getDir(context, DOWNLOAD);
    }

    public static String getCacheDir(Context context) {
        return getDir(context, CACHE);
    }

    public static String getIconDir(Context context) {
        return getDir(context, ICON);
    }

    public static String getImageDir(Context context) {
        return getDir(context, IMAGE);
    }

    public static String getScreenDir(Context context) {
        return getDir(context, SCREEN);
    }

    public static String getLogDir(Context context) {
        return getDir(context, LOG);
    }

    public static String getWebCacheDir(Context context) {
        return getDir(context, WEBCACHE);
    }

    public static String getDir(Context context, String name) {
        String path;
        if (isSDCardAvailable()) {
            path = getExternalStoragePath();
        } else {
            path = getCachePath(context);
        }
        path = path + name + "/";
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    public static String getCachePath(Context context) {
        File f = context.getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 获取闪起页面背景图片地址
     *
     * @param context
     * @return
     */
    public static String getWelcomPicFileDir(Context context) {
        return FileUtils.getIconDir(context) + "/" + WELCOME_PIC;
    }
}
