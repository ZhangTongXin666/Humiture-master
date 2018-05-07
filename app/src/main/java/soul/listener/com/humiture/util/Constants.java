package soul.listener.com.humiture.util;

/**
 * @description 常量类
 */
public class Constants {

    private Constants() {}
    // 打印日志开关
    public static final boolean DEBUG = true;

    //远程数据库账号
    //public static final String SQLITEURL = "jdbc:mysql://120.24.52.34:3306/housetem";
    public static final String SQLITEURL = "jdbc:mysql://114.115.172.62:3306/tmdb";
    //远程数据库账号
    public static final String SQLITEUSER = "appuser";
    //远程数据库密码
    public static final String SQLITEPW = "Wrj,0606";

    //默认 SharePreferences文件名
    public static final String SHARED_PATH = "soul.listener.com.humiture";

    public static final String LIKE = " like ";
    public static final String IS = " = ";

    //数据库列表序号
    public static final int SYSUSER_TABLE_NO = 1; //系统用户
    public static final int BLOCKS_TABLE_NO = 2; //街区
    public static final int RESIDENT_TABLE_NO = 3;//居民
    public static final int LOG_TABLE_NO = 4;//日志表
    public static final int TEMPERATURE_TABLE_NO = 5;//温度
    //数据库表名
    public static final String SYSUSER_TABLE = "sysuser_table";
    public static final String BLOCKS_TABLE = "blocks_table";
    public static final String RESIDENT_TABLE = "resident_table";
    public static final String LOG_TABLE = "log_table";
    public static final String TEMPERATURE_TABLE = "temperature_table";
    //sysuser_table列名
    public static final String SYSUSER_ID = "id";
    public static final String SYSUSER_USERID = "userid";
    public static final String SYSUSER_USERNAME = "username";
    public static final String SYSUSER_PASSWORD = "userpassword";
    public static final String SYSUSER_USERPRIVILEGES = "userpPrivileges";//特权
    public static final String SYSUSER_USERTYPE = "userType";//用户类型
    public static final String SYSUSER_USERMAIL = "userMail";//用户邮件
    public static final String SYSUSER_USERTEL = "userTel";//用户电话
    //block 列名
    public static final String BLOCK_ID = "blocksID";//街区ID
    public static final String BLOCK_NAME = "blocksName";//街区名称
    public static final String BLOCK_LOACTION = "blocksLocation";//街区取位置
    public static final String BLOCK_CONTACT = "blocksContact";//街区联系地址
    public static final String BLOCK_CONTACETEL = "blocksContaceTel";//街区联系电话

    /*居民 列名*/
    public static final String RESIDENT_ID = "residentID";
    public static final String RESIDENT_NAME = "residentName";
    public static final String RESIDENT_BLOCAKSID = "blocksID";
    public static final String RESIDENT_BUILDINGNO = "buildingNo";
    public static final String RESIDENT_UNIT = "residentUnit";
    public static final String RESIDENT_ROOM = "residentRoomNo";
    public static final String RESIDENT_TEL1 = "residentTel1";
    public static final String RESIDENT_TEL2 = "residentTel2";
    public static final String RESIDENT_TEL3 = "residentTel3";
    public static final String RESIDENT_IEEE = "residentIEEEAddress";

    /*温湿度 列名*/
    public static final String HUMITURE_ID = "ID";
    public static final String HUMITURE_RESIDENTID = "residentID";
    public static final String HUMITURE_TEMPERATURE = "temperature";
    public static final String HUMITURE_HUMIDUTY = "humidity";
    public static final String HUMITURE_CURRENTDATE = "currentDate";
    public static final String HUMITURE_CURRENTTIME = "currentTime";
    public static final String HUMITURE_OUT = "temperatureOut";

    /*登录错误码*/
    public static final int error_allEmpty = 0;//用户名和密码全为空
    public static final int error_usernameEmpty = 1;//用户名为空
    public static final int error_passwordEmpty = 2;//密码为空
    public static final int error_usernameNoExist = 3;//用户名不存在
    public static final int error_passowordNoSure = 4;//密码不正确

    /*popWindow显示内容码*/
    public static final int SHOW_BLOCK = 0;//小区
    public static final int SHOW_BUILDINGINDEX = 1;//楼号
    public static final int SHOW_UNIT = 2;//单元
    public static final int SHOW_HOMENUMBER = 3;//房间号
    public static final int SHOW_DATACOUNT = 4;//数据量
    public static final int SHOW_PAGACOUNT = 5;//页数
    public static final int SHOW_BLOCKLOCATION = 6;//小区位置

    /*标准室内温湿度常量*/
    public static final int STAND_LOW_TEMPERATURE = 18;
    public static final int STAND_HIGHT_TEMPERATURE = 28;
    public static final int STAND_LOW_HUMIDITY = 30;
    public static final int STAND_HIGHT_HUMIDITY = 60;

    /*本地数据库*/
    public static final String DATEBASE_NAME = "record";
    public static final int DATEBASE_VERSION = 1;
    public static final String TABLE_NAME = "record";

    /*常量控制X坐标值*/
    public static int num = 1;

    public static final String PHONEFORMAT_NOSURE = "请正确输入手机号";

}

