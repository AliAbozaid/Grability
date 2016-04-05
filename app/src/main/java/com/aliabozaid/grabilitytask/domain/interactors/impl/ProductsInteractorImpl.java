package com.aliabozaid.grabilitytask.domain.interactors.impl;

import com.aliabozaid.grabilitytask.BuildConfig;
import com.aliabozaid.grabilitytask.domain.contollers.ProductController;
import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aliabozaid on 3/26/16.
 */
public class ProductsInteractorImpl implements ResponseCallback {

    private ResponseCallback.MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;


    public ProductsInteractorImpl(Retrofit retrofit, String url, ResponseCallback.MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        getProducts(retrofit, url);

    }

    public void getProducts(Retrofit retrofit, String url) {
        ProductController products = retrofit.create(ProductController.class);
        products.getProducts(url, BuildConfig.LIMIT, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListOfProductsModel>() {
                    @Override
                    public void onCompleted() {
                        presenterCallBack.showLoading(false);
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.error(e);
                        this.unsubscribe();
                    }

                    @Override
                    public void onNext(ListOfProductsModel listOfProductsModel) {
                        mCallback.success(listOfProductsModel);
                    }
                });

    }


    @Override
    public void unsubscribe() {

    }
}
