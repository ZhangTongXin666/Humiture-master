package soul.listener.com.humiture.a_presenters;

/**
 * Created by kys_31 on 2017/11/30.
 */

public interface IBasePresenter<T>{
    void attachView(T view);
    void detachView();
}
