package com.aliabozaid.grabilitytask;

import android.app.Application;

import com.aliabozaid.grabilitytask.domain.contollers.ApiComponent;
import com.aliabozaid.grabilitytask.domain.contollers.DaggerApiComponent;
import com.aliabozaid.grabilitytask.domain.module.ApiModule;

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
