package com.example.study3_imagelist;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;


public class MyModel implements MyContract.Model{

    private ArrayList<Record> results = new ArrayList<>();
    boolean allDataLoaded = false;

    public ArrayList<Record> getRecords() {
        return results;
    }

    @Override
    public void PreLoadImages(MyView myView) {
        Uri sourceUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(myView,
                sourceUri,
                projection,
                null,
                null,
                MediaStore.Images.Media.DATE_ADDED);

        Cursor cursor = cursorLoader.loadInBackground();

        while (cursor.moveToNext()) {
            String mName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            String mID = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            Record rec = new Record(mID, mName, null, true);
            results.add(rec);
            cursor.moveToNext();
        }
        cursor.close();
    }

    @Override
    public void LoadImagesContent() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int origWidth, origHeight, newWidth, newHeight;

                for (int i = 0; i < results.size(); i++) {
                    Record record = results.get(i);
                    Bitmap orig = BitmapFactory.decodeFile(record.ID);

                    origWidth = orig.getWidth();
                    origHeight = orig.getHeight();

                    if (origWidth < origHeight) {
                        newWidth = 150 * origWidth / origHeight;
                        newHeight = 150;
                    } else {
                        newHeight = 150 * origHeight / origWidth;
                        newWidth = 150;
                    }
                    ;

                    Bitmap bmp = Bitmap.createScaledBitmap(orig, newWidth, newHeight, false);
                    record.icon = bmp;
                    record.iconEmpty = false;

                };
                allDataLoaded = true;
            }
        });
        t.start();
    }

    public boolean allDataLoaded(){
        return allDataLoaded;
    }

}
