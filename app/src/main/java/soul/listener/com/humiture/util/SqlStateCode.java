package soul.listener.com.humiture.util;

/**
 * @author kuan
 *         Created on 2017/11/17.
 * @description
 */

public class SqlStateCode {
    public static final int STSTE_NODATA = 0; //没有数据
    public static final int STSTE_NOINTERFACE = 1; //后台没有开启
    public static final int STSTE_PHONENO = 2; //该手机号没有注册

    /**
     * 根据状态码返回状态
     * @param num  状态码
     * @return 状态
     */
    public static String getSqlFaildInfo(int num) {
        String sqlFaildInfo = null;
        switch (num) {
            case STSTE_NODATA:
                sqlFaildInfo = "没有数据";
                break;
            case 1:
                sqlFaildInfo = "系统维护";
                break;
            case 2:
                sqlFaildInfo = "该手机号没有注册";
            default:
                break;
        }
        return sqlFaildInfo;
    }
}
