package soul.listener.com.humiture.util;

import soul.listener.com.humiture.R;

/**
 * @author kuan
 *         Created on 2017/11/16.
 * @description
 */

public class StringUtils {

    /**
     * 根据表ID生成表名称
     * @param tableNo 表ID
     * @return  表名称
     */
    public static String dealTableName(int tableNo){
        String tableName = null;
        switch (tableNo){
            case Constants.SYSUSER_TABLE_NO:
                tableName = Constants.SYSUSER_TABLE;
                break;
            case Constants.BLOCKS_TABLE_NO:
                tableName = Constants.BLOCKS_TABLE;
                break;
            case Constants.RESIDENT_TABLE_NO:
                tableName = Constants.RESIDENT_TABLE;
                break;
            case Constants.LOG_TABLE_NO:
                tableName = Constants.LOG_TABLE;
                break;
            case Constants.TEMPERATURE_TABLE_NO:
                tableName = Constants.TEMPERATURE_TABLE;
                break;
            default:
                break;
        }
        return tableName;
    }

    /**
     * 根据登录错误码返回错误信息
     * @param errorCode 错误码
     * @return 错误信息
     */
    public static String getFailureMessage(int errorCode){
        String failureMessage = null;
        switch (errorCode){
            case 0:
                failureMessage = MyApplication.getContext().getResources().getString(R.string.allEmpty);
                break;
            case 1:
                failureMessage = MyApplication.getContext().getResources().getString(R.string.usernameEmpty);
                break;
            case 2:
                failureMessage = MyApplication.getContext().getResources().getString(R.string.passwordEmpty);
                break;
            case 3:
                failureMessage = MyApplication.getContext().getResources().getString(R.string.usernameNoExist);
                break;
            case 4:
                failureMessage = MyApplication.getContext().getResources().getString(R.string.passwordNoSure);
                break;
                default:break;
        }
        return failureMessage;
    }
}
