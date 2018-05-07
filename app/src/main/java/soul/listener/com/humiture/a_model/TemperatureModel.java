package soul.listener.com.humiture.a_model;

/**
 * @author kuan
 *         Created on 2017/11/14.
 * @description
 */

public class TemperatureModel extends SqlFactory{
    private String id;
    private String residentId;//居民ID
    private String temperature;//温度
    private String humidity;//温湿度
    private String currentDate;//当前日期
    private String currentTime;//当前时间
    private String temperatureOut;//超出温度

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTemperatureOut() {
        return temperatureOut;
    }

    public void setTemperatureOut(String temperatureOut) {
        this.temperatureOut = temperatureOut;
    }

    @Override
    public String toString() {
        return "TemperatureModel{" +
                "id='" + id + '\'' +
                ", residentId='" + residentId + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", temperatureOut='" + temperatureOut + '\'' +
                '}';
    }
}
