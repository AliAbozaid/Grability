package com.aliabozaid.grabilitytask.singleton;

import android.app.Application;

import com.aliabozaid.grabilitytask.contollers.ApiComponent;
import com.aliabozaid.grabilitytask.contollers.DaggerApiComponent;
import com.aliabozaid.grabilitytask.module.ApiModule;

import java.io.File;

public class MyApplication extends Application {

    ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        apiComponent = DaggerApiComponent.builder().apiModule(new ApiModule(cacheFile)).build();

    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

}
