package com.example.study3_imagelist;

import android.graphics.Bitmap;

public class Record {
    public String ID;
    public String name;
    public Bitmap icon;
    public Boolean iconEmpty;

    public Record(String ID, String name, Bitmap icon, Boolean iconEmpty) {
        this.ID = ID;
        this.name = name;
        this.icon = icon;
        this.iconEmpty = iconEmpty;

    }
}
