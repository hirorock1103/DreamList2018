package com.example.user.dreamlist2018;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DreamManager extends MyDbHelper {
    public DreamManager(Context context) {
        super(context);
    }

    //DREAM


    public long addDream(Dream dream){

        ContentValues values = new ContentValues();

        values.put(DREAM_COLUMN_TITLE, dream.getTitle());
        values.put(DREAM_COLUMN_DESCRIPTION,dream.getDescription());
        values.put(DREAM_COLUMN_DEADLINE, dream.getDeadline());
        values.put(DREAM_COLUMN_CREATEDATE, Common.formatDate(new Date(), Common.DB_DATE_FORMAT ));

        SQLiteDatabase db = getWritableDatabase();
        long insertId =  db.insert(TABLE_DREAM, null, values);

        return insertId;

    }


    public List<Dream> getDreamList(){

        List<Dream> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_DREAM + " ORDER BY " + DREAM_COLUMN_ID + " DESC ";

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            Dream dream = new Dream();

            dream.setId(c.getInt(c.getColumnIndex(DREAM_COLUMN_ID)));
            dream.setTitle(c.getString(c.getColumnIndex(DREAM_COLUMN_TITLE)));
            dream.setDescription(c.getString(c.getColumnIndex(DREAM_COLUMN_DESCRIPTION)));
            dream.setDeadline(c.getString(c.getColumnIndex(DREAM_COLUMN_DEADLINE)));
            dream.setCreatedate(c.getString(c.getColumnIndex(DREAM_COLUMN_CREATEDATE)));

            list.add(dream);

            c.moveToNext();
        }

        return list;

    }


}
