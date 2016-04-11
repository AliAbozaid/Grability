package com.aliabozaid.grabilitytask.domain.interactors;


/**
 * Created by aliabozaid on 3/17/16.
 */
public interface ResponseCallback {
    void unsubscribe();

    interface MYCallback {
        public void success(Object data);

        public void error(Throwable t);
    }

}
