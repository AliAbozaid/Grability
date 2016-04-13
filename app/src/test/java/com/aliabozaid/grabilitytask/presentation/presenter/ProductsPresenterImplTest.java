package com.aliabozaid.grabilitytask.presentation.presenter;

import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by aliabozaid on 4/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductsPresenterImplTest implements ResponseCallback.MYCallback  {
    @Mock MainPresenter.PresenterCallBack presenterCallBack;
    @Mock Throwable throwable;
    @Mock ListOfProductsModel listOfProductsModel;


    @Test
    public void productSuccessSubscriberCalled()
    {
        listOfProductsModel = new ListOfProductsModel();
        presenterCallBack.updateView(listOfProductsModel);

    }
    @Test
    public  void productErrorSubscriberCalled()
    {
        throwable = new Throwable();
        presenterCallBack.showConnectionError(throwable);
    }

    @Override
    public void success(Object data) {
        assertEquals(listOfProductsModel, data);
    }

    @Override
    public void error(Throwable t) {
        assertEquals(t, throwable);
    }
}
