package com.example.study3_imagelist;

import android.app.Application;

public class MainApp extends Application {
    private MyContract.Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        MyPresenter.initInstance();
        presenter = MyPresenter.getInstance();
    }

    public MyContract.Presenter getPresenter(){
        return presenter;
    }
}
