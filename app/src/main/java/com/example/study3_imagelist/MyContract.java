package com.example.study3_imagelist;

import java.util.ArrayList;

public interface MyContract {

    interface View {
        public MyAdapter getAdapter();
        public void setAdapterOnRecycler(MyAdapter adapter);
    }

    interface Presenter {
        public void attachView(MyView view);
        public void detachView();
        public void refreshData();
        public boolean allDataLoaded();
    }

    interface Model {
        public void PreLoadImages(MyView myView);
        public void LoadImagesContent();
        public ArrayList<Record> getRecords();
        public boolean allDataLoaded();
    }

}
