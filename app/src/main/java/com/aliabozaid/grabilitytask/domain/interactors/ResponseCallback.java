package com.aliabozaid.grabilitytask.domain.interactors;


import com.aliabozaid.grabilitytask.domain.interactors.base.Interactor;

/**
 * Created by aliabozaid on 3/17/16.
 */
public interface ResponseCallback {
    interface MYCallback {
        public void success(Object data);

        public void error(Throwable t);
    }

}
