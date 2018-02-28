package com.zia.bihu.Util;

import android.util.Log;

import com.zia.bihu.Bean.UserData;
import com.zia.bihu.Model.InternetModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zia on 2018/2/19.
 */
public class App {
    private volatile static App instance;

    private InternetModel internetModel;
    private UserData selfData;
    private ExecutorService threadPool;

    public UserData getSelfData() {
        return selfData;
    }

    public void setSelfData(UserData selfData) {
        this.selfData = selfData;
        Log.e("token",selfData.getToken());
    }

    public InternetModel getInternetModel() {
        return internetModel;
    }

    public void startNewThread(Runnable runnable){
        threadPool.execute(runnable);
    }

    private App() {
        init();
    }

    private void init(){
        internetModel = new InternetModel();
        threadPool = Executors.newCachedThreadPool();
    }

    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }


}
