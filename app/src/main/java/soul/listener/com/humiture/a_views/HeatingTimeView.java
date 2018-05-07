package soul.listener.com.humiture.a_views;

/**
 * Created by 流月 on 2018/4/23.
 *
 * @description
 */

public interface HeatingTimeView {
    void setAddress(String str);
    void setNextClick(boolean click);
    void setUpClick(boolean click);
    void userLogin();

    String getAddress();
}
