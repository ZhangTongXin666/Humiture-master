package soul.listener.com.humiture.a_presenters;

/**
 * @description Presenter基类
 */

public abstract class BasePresenter<V> implements IBasePresenter<V> {
    protected V mView;

    public BasePresenter(V v) {
        attachView(v);
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }



}
