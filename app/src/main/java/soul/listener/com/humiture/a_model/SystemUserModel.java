package soul.listener.com.humiture.a_model;

/**
 * @author kuan
 *         Created on 2017/11/13.
 * @description
 */

public class SystemUserModel extends SqlFactory{
    private String id;
    private String userId;
    private String userName;
    private String userPassword;
    private String userpPrivaileges;//用户特权
    private String userType;
    private String userMail;
    private String userTel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserpPrivaileges() {
        return userpPrivaileges;
    }

    public void setUserpPrivaileges(String userpPrivaileges) {
        this.userpPrivaileges = userpPrivaileges;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "SystemUserModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userpPrivaileges='" + userpPrivaileges + '\'' +
                ", userType='" + userType + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
