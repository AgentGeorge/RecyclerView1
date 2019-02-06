package com.example.study3_imagelist;


import java.util.ArrayList;

public class MyPresenter {

    private MyView mainView;
    private MyModel model;

    public void attachView(MyView view) {
        mainView = view;
        model = new MyModel(view);
    }

    public void detachView() {
        mainView = null;
//        model = null;
    }

    public void ViewStarted(ArrayList<Record> records) {
        model.LoadImagesContent(records);
    }

    public Record getRecord(int i) {
        return model.getRecord(i);
    }

    public int getItemCount() {
        return model.getItemCount();
    }




}
