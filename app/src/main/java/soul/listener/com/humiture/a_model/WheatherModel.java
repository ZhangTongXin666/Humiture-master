package soul.listener.com.humiture.a_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyue on 2017/12/3.
 */

public class WheatherModel {
    private String city;
    private String updatetime;
    private String wendu;
    private String fengli;
    private String shidu;
    private String fengxiang;
    private String sunrise;
    private String sunset;
    private String aqi;
    private String pm25;
    private String suggest;
    private String quality;
    private String MajorPollutants;
    private String o3;
    private String co;
    private String pm10;
    private String so2;
    private String no2;
    private String time;
    private List<String> date_List=new ArrayList<>();
    private List<String> high_List=new ArrayList<>();
    private List<String> low_List=new ArrayList<>();
    private List<String> day_type_List=new ArrayList<>();
    private List<String> day_fengxiang_List=new ArrayList<>();
    private List<String> day_fengli_List=new ArrayList<>();
    private List<String> night_type_List=new ArrayList<>();
    private List<String> night_fengxiang_List=new ArrayList<>();
    private List<String> night_fengli_List=new ArrayList<>();

    public void appendDate_List(String date) {
        this.date_List.add(date);
    }

    public void appendHigh_List(String high) {
        this.high_List.add(high);
    }

    public void appendLow_List(String low) {
        this.low_List .add(low);
    }

    public void appendDay_type_List(String day_type) {
        this.day_type_List .add(day_type);
    }

    public void appendDay_fengxiang_List(String day_fengxiang) {
        this.day_fengxiang_List .add(day_fengxiang);
    }

    public void appendDay_fengli_List(String day_fengli) {
        this.day_fengli_List .add(day_fengli);
    }

    public void appendNight_type_List(String night_type) {
        this.night_type_List .add(night_type);
    }

    public void appendNight_fengxiang_List(String night_fengxiang) {
        this.night_fengxiang_List .add(night_fengxiang);
    }

    public void appendNight_fengli_List(String night_fengli) {
        this.night_fengli_List.add(night_fengli);
    }



    public List<String> getDate_List() {
        return date_List;
    }

    public List<String> getHigh_List() {
        return high_List;
    }

    public List<String> getLow_List() {
        return low_List;
    }

    public List<String> getDay_type_List() {
        return day_type_List;
    }

    public List<String> getDay_fengxiang_List() {
        return day_fengxiang_List;
    }

    public List<String> getDay_fengli_List() {
        return day_fengli_List;
    }

    public List<String> getNight_type_List() {
        return night_type_List;
    }

    public List<String> getNight_fengxiang_List() {
        return night_fengxiang_List;
    }

    public List<String> getNight_fengli_List() {
        return night_fengli_List;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setMajorPollutants(String majorPollutants) {
        MajorPollutants = majorPollutants;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {

        return city;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getWendu() {
        return wendu;
    }

    public String getFengli() {
        return fengli;
    }

    public String getShidu() {
        return shidu;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getAqi() {
        return aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public String getSuggest() {
        return suggest;
    }

    public String getQuality() {
        return quality;
    }

    public String getMajorPollutants() {
        return MajorPollutants;
    }

    public String getO3() {
        return o3;
    }

    public String getCo() {
        return co;
    }

    public String getPm10() {
        return pm10;
    }

    public String getSo2() {
        return so2;
    }

    public String getNo2() {
        return no2;
    }

    public String getTime() {
        return time;
    }


}
