package com.example.study3_imagelist;


import java.util.ArrayList;

public final class MyPresenter implements MyContract.Presenter{

    private static MyPresenter mInstance;
    private MyContract.View mainView;
    private MyContract.Model model;

    public static void initInstance() {
        if (mInstance == null) {
            mInstance = new MyPresenter();
        }
    }

    public static MyPresenter getInstance() {
        return mInstance;
    }

    @Override
    public void attachView(MyView view) {
        mainView = view;
        if (model == null) {
            model = new MyModel();
            model.PreLoadImages(view);
            model.LoadImagesContent();
        }

        MyAdapter adapter = new MyAdapter(model.getRecords());
        mainView.setAdapterOnRecycler(adapter);
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public boolean allDataLoaded(){
        return model.allDataLoaded();
    }

    @Override
    public void refreshData(){
        if (mainView != null) {
            MyAdapter adapter = mainView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

}
