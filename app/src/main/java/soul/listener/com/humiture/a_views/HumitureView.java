package soul.listener.com.humiture.a_views;

/**
 * Created by kys_31 on 2017/12/5.
 */

public interface HumitureView {

    void setAddress(String str);
    void setNextClick(boolean click);
    void setUpClick(boolean click);
    void userLogin();
    void setDate(String data);
    String getDate();
    String getAddress();
}
