package soul.listener.com.humiture.a_views;

import java.util.List;



/**
 * Created by liuyue on 2017/12/3.
 */

public interface WheatherView {
    void setDayDate(List<String> list);
    void setHigh(List<String> list);
    void setLow(List<String> list);
    void setDayType(List<String> list);
    void setDayfengxiang(List<String> list);
    void setDayfengli(List<String> list);
    void setNightType(List<String> list);
    void setNightfengxiang(List<String> list);
    void setNightfengli(List<String> list);
    void setNowQuality(String str);
    void setNowQualityNumber(String str);
    void setTodayFengXiang(String str);
    void setTodayFengLi(String str);
    void setTodayHumidity(String str);
    void setTodaySunrise(String str);
    void setTodaySunset(String str);
    void setLocation(String str);
    void setWendu(String str);
    void setNowType(String str);
    void getWeatherFailure(String str);
    void getWeatherSuccess();
}
