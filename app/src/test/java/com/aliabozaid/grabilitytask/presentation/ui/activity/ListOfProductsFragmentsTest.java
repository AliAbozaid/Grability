package com.aliabozaid.grabilitytask.presentation.ui.activity;

import com.aliabozaid.grabilitytask.domain.interactors.ResponseCallback;
import com.aliabozaid.grabilitytask.domain.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by aliabozaid on 4/13/16.
 */
@RunWith(MockitoJUnitRunner.class)
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
