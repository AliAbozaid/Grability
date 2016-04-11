package com.aliabozaid.grabilitytask.domain.interactors.impl;

import com.aliabozaid.grabilitytask.BuildConfig;
import com.aliabozaid.grabilitytask.domain.contollers.ProductController;
import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.interactors.base.DefaultSubscriber;
import com.aliabozaid.grabilitytask.domain.interactors.base.UseCase;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by aliabozaid on 4/5/16.
 */
public class ProductsInteractorImpl extends UseCase implements ResponseCallback {

    private ResponseCallback.MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    ProductController products;
    String url;

    public ProductsInteractorImpl(Retrofit retrofit, String url, ResponseCallback.MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.url = url;
        products = retrofit.create(ProductController.class);

        this.execute(new ProductSubscriber());


    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.products.getProducts(url, BuildConfig.LIMIT, "json");
    }


    @RxLogSubscriber
    private final class ProductSubscriber extends DefaultSubscriber<ListOfProductsModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(ListOfProductsModel listOfProductsModel) {
            mCallback.success(listOfProductsModel);
        }
    }
}
