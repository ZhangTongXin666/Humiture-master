package soul.listener.com.humiture.a_model;

/**
 * @author kuan
 *         Created on 2017/11/14.
 * @description
 */

public class LogModel extends SqlFactory{
    private String id;
    private String userId;
    private String operation;
    private String currentTime;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", operation='" + operation + '\'' +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}
