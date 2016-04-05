package com.aliabozaid.grabilitytask.presentation.presenter.impl;


import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.interactors.impl.ProductsInteractorImpl2;
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;

import retrofit2.Retrofit;

/**
 * Created by aliabozaid on 3/26/16.
 */
public class ProductsPresenterImpl implements MainPresenter, ResponseCallback.MYCallback {
    PresenterCallBack presenterCallBack;
    ResponseCallback responseCallback;

    public ProductsPresenterImpl(Retrofit retrofit, String url, PresenterCallBack presenterCallBack) {
        this.presenterCallBack = presenterCallBack;
        responseCallback = new ProductsInteractorImpl2(retrofit, url, this, presenterCallBack);
    }


    @Override
    public void success(Object data) {
        presenterCallBack.updateView(data);
    }

    @Override
    public void error(Throwable t) {
        presenterCallBack.showConnectionError(t);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        responseCallback.unsubscribe();
    }
}
