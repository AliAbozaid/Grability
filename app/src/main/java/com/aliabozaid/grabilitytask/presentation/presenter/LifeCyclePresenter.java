package com.aliabozaid.grabilitytask.presentation.presenter;

/**
 * Created by aliabozaid on 4/5/16.
 */
public interface LifeCyclePresenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStart() method.
     */
    void onStart();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onCreate() method.
     */
    void onCreate();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void onResume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void onPause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    void onStop();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void onDestroy();
}
