package soul.listener.com.humiture.util;

import android.app.Application;
import android.content.Context;

import com.mysql.jdbc.Connection;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @description 获取整个应用的上下文
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static Context mContext;
    public static Connection connection;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
       // initLeakCanary();//检测内存溢出
        CrashReport.initCrashReport(getApplicationContext(), "cb7f2d8fee", true);
        initLogger();//初始化日志管理
        AutoLayoutConifg.getInstance().useDeviceSize();
        initData();//初始化远程数据库
    }

    //初始化数据
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = (Connection) DriverManager.getConnection(Constants.SQLITEURL,Constants.SQLITEUSER,Constants.SQLITEPW);
                    if (connection == null){
                        connection = (Connection) DriverManager.getConnection(Constants.SQLITEURL,Constants.SQLITEUSER,Constants.SQLITEPW);
                    }
                } catch (ClassNotFoundException e) {
                    Logger.e("连接数据库错误========="+e.toString());
                    e.printStackTrace();
                } catch (SQLException e) {
                    Logger.e("数据库错误========="+e.toString());
                    e.printStackTrace();
                }
            }
        }).start();


    }

    /**
     * 检测内存溢出
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 初始化日志管理
     */
    private void initLogger() {
        if (Constants.DEBUG) {
            Logger.init().methodCount(0).hideThreadInfo().logLevel(LogLevel.FULL);
        } else {
            Logger.init().methodCount(0).hideThreadInfo().logLevel(LogLevel.NONE);
        }
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决安卓5.0以下手机不能编译运行
     /*   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && BuildConfig.DEBUG) {
            MultiDex.install(this);
        }*/
    }

}
