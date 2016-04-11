package com.aliabozaid.grabilitytask.presentation.presenter.impl;


import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.interactors.impl.ProductsInteractorImpl;
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
        responseCallback = new ProductsInteractorImpl(retrofit, url, this, presenterCallBack);
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
    public void onStart() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        responseCallback.unsubscribe();
    }

}
