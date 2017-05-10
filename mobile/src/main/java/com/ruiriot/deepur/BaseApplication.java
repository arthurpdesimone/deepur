package com.ruiriot.deepur;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.google.common.eventbus.Subscribe;

import static com.ruiriot.deepur.Constants.TAG_ERROR;
import static com.ruiriot.deepur.Event.register;

/**
 * Created by ruiri on 09-May-17.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        register(this);
    }

    @Subscribe
    public void handleException(BaseException e){
        Log.e(TAG_ERROR, "ERRO");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
