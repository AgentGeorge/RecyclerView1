package com.example.study3_imagelist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MyView extends Activity {
    MyAdapter adapter;
    Handler handler;
    private ArrayList<Record> results = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                adapter.notifyItemChanged(msg.what);
            }
        };

        MyPresenter presenter = new MyPresenter(results, this);
        presenter.ViewStarted();

        adapter = new MyAdapter(results);
        adapter.presenter = presenter;
        mRecyclerView.setAdapter(adapter);

    }

}

