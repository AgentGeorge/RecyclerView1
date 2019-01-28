package com.example.study3_imagelist;


import java.util.ArrayList;

public class MyPresenter {

    private MyView view;
    private MyModel model;

    public MyPresenter(ArrayList<Record> results, MyView view) {
        this.view = view;
        this.model = new MyModel(results, view);
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
