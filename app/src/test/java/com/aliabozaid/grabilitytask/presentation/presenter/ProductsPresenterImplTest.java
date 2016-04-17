package com.aliabozaid.grabilitytask.presentation.presenter;

import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by aliabozaid on 4/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductsPresenterImplTest implements ResponseCallback.MYCallback  {
    @Mock ResponseCallback.MYCallback presenterCallBack;
    @Mock Throwable throwable;
    @Mock ListOfProductsModel listOfProductsModel;


    @Before
    public  void intialize()
    {
        presenterCallBack = this;
        throwable = new Throwable("my error");
        listOfProductsModel = new ListOfProductsModel();
        listOfProductsModel.feed = new ListOfProductsModel().new Feed();
        listOfProductsModel.feed.author = new ListOfProductsModel().new Feed().new Author();
        listOfProductsModel.feed.author.uri = new ListOfProductsModel().new Feed().new Author().new Uri();
        listOfProductsModel.feed.author.uri.label = "test";

    }
    @Test
    public void productSuccessSubscriberCalled()
    {

        presenterCallBack.success(listOfProductsModel);

    }
    @Test
    public  void productErrorSubscriberCalled()
    {

        presenterCallBack.error(throwable);
    }

    @Override
    public void success(Object data) {
        assertNotNull(data);
        assertEquals(listOfProductsModel, data);
    }

    @Override
    public void error(Throwable t) {
        assertNotNull(t);
        assertEquals(t, throwable);
    }
}
