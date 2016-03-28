package com.aliabozaid.grabilitytask.presentation.presenter;

/**
 * Created by aliabozaid on 3/26/16.
 */
public interface MainPresenter {
    public interface PresenterCallBack{
        void showLoading(boolean show);

        void showConnectionError(Throwable t);

        void updateView(Object data);
    }

}
