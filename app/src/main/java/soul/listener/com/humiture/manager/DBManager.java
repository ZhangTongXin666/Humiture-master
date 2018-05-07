package soul.listener.com.humiture.manager;

/**
 * @description 链接远程数据库
 * Created by kuan on 2017/10/24.
 */

public class DBManager {

    //获取操作远程数据库实例
    private static DBManager dbManager;
    private DBManager(){}

    public static DBManager getDBManager(){
        if (dbManager == null){
            synchronized(DBManager.class) {
                if (dbManager == null) {
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    public static void doQuery(){
    }
}
