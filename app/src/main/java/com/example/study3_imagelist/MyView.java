package com.example.study3_imagelist;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MyView extends Activity implements MyContract.View{
    private MyAdapter adapter;
    RecyclerView recyclerView;
//    Handler handler;
    MyContract.Presenter presenter;
    private ArrayList<Record> records = new ArrayList<>();
    final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int permissionStatus = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permission granted
            init();
        }
    }

    public void init(){
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainApp mainApp = ((MainApp) getApplicationContext());
        presenter = mainApp.getPresenter();
        presenter.attachView(this);

//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                adapter.notifyItemChanged(msg.what);
//            }
//        };

        runOnUiThread(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!presenter.allDataLoaded()) {
                            presenter.refreshData();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
        }));

    }

    @Override
    public MyAdapter getAdapter(){
        return adapter;
    }

    @Override
    public void setAdapterOnRecycler(MyAdapter adapter){
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.detachView();
    }

}

