package com.aliabozaid.grabilitytask.presentation.ui.activity;

import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

/**
 * Created by aliabozaid on 4/13/16.
 */
public class ListOfProductsFragmentsTest implements MainPresenter.PresenterCallBack, ResponseCallback.MYCallback  {
    @Mock ResponseCallback.MYCallback myCallback;
    @Mock Throwable throwable;
    @Mock ListOfProductsModel listOfProductsModel;
    @Mock MainPresenter.PresenterCallBack presenterCallBack;

    @Before
    public  void intialize()
    {
        presenterCallBack = this;
        myCallback = this;
        listOfProductsModel = new ListOfProductsModel();
        throwable = new Throwable();
    }
    @Test
    public void productSuccessSubscriberCalled()
    {

        presenterCallBack.updateView(listOfProductsModel);

    }
    @Test
    public  void productErrorSubscriberCalled()
    {
        presenterCallBack.showConnectionError(throwable);
    }


    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showConnectionError(Throwable t) {
        assertEquals(throwable, t);
    }

    @Override
    public void updateView(Object data) {
        assertEquals(listOfProductsModel, data);
    }

    @Override
    public void success(Object data) {
        myCallback.success(data);
    }

    @Override
    public void error(Throwable t) {
        myCallback.success(t);
    }
}
