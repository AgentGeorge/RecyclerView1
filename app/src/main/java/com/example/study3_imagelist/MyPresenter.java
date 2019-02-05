package com.example.study3_imagelist;


import java.util.ArrayList;

public class MyPresenter {

    private MyView mainView;
    private MyModel model;
    private ArrayList<Record> records;

    public void MyPresenter(){
        records = new ArrayList<>();
    }


    public void attachView(MyView view) {
        mainView = view;
    }

    public void detachView() {
        mainView = null;
    }

    public Record getRecord(int i) {
        return model.getRecord(i);
    }


    public void ViewStarted() {
        model.LoadImagesContent();
    }

    public int getItemCount() {
        return model.getItemCount();
    }



}
