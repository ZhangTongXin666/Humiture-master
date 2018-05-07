package soul.listener.com.humiture.a_views;

/**
 * Created by kys_31 on 2017/11/30.
 */

public interface LoginView {
    void loginSuccess();
    void loginFailture(String failureMessage);
    String getUsername();
    String getPassword();
}
