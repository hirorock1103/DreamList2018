package com.example.user.dreamlist2018;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    private final static int DBVERSION = 1;
    private final static String DBNAME = "Dreamlist.db";

    protected final static String TABLE_DREAM = "Dream";
    protected final static String DREAM_COLUMN_ID = "id";
    protected final static String DREAM_COLUMN_TITLE = "title";
    protected final static String DREAM_COLUMN_DESCRIPTION = "description";
    protected final static String DREAM_COLUMN_DEADLINE = "deadline";
    protected final static String DREAM_COLUMN_CREATEDATE = "createdate";

    protected final static String TABLE_TODO = "Todo";
    protected final static String TODO_COLUMN_ID = "id";
    protected final static String TODO_COLUMN_TITLE = "title";
    protected final static String TODO_COLUMN_DREAMID = "dream_id";
    protected final static String TODO_COLUMN_DESCRIPTION = "description";
    protected final static String TODO_COLUMN_ISFINISHED = "is_finished";
    protected final static String TODO_COLUMN_PERCENTAGE = "percentage";
    protected final static String TODO_COLUMN_CREATEDATE = "createdate";

    protected final static String TABLE_TODO_HISTORY = "TodoHistory";
    protected final static String TODO_HISTORY_COLUMN_ID = "id";
    protected final static String TODO_HISTORY_COLUMN_TODO_ID = "todo_id";
    protected final static String TODO_HISTORY_COLUMN_TODO_DETAILS = "todo_details";
    protected final static String TODO_HISTORY_COLUMN_CREATEDATE = "createdate";

    protected final static String TABLE_MOTIVATIONITEM = "MotivationItem";
    protected final static String MOTIVATIONITEM_COLUMN_ID = "id";
    protected final static String MOTIVATIONITEM_COLUMN_DREAM_ID = "dream_id";
    protected final static String MOTIVATIONITEM_COLUMN_TITLE = "title";
    protected final static String MOTIVATIONITEM_COLUMN_IMAGEBYTES = "image_bytes";
    protected final static String MOTIVATIONITEM_COLUMN_ISTRASH = "is_trash";


    public MyDbHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_DREAM + "(" +
                DREAM_COLUMN_ID + " integer primary key autoincrement," +
                DREAM_COLUMN_TITLE + " text," +
                DREAM_COLUMN_DESCRIPTION + " text," +
                DREAM_COLUMN_DEADLINE + " text," +
                DREAM_COLUMN_CREATEDATE + " text" +
                ")";

        db.execSQL(query);


        query = "CREATE TABLE IF NOT EXISTS " + TABLE_TODO + "(" +
                TODO_COLUMN_ID + " integer primary key autoincrement," +
                TODO_COLUMN_TITLE + " text," +
                TODO_COLUMN_DREAMID + " integer default 0," +
                TODO_COLUMN_DESCRIPTION + " text," +
                TODO_COLUMN_ISFINISHED + " integer default 0, " +
                TODO_COLUMN_PERCENTAGE + " integer default 0, " +
                TODO_COLUMN_CREATEDATE + " text " +
                ")";

        db.execSQL(query);


        query = "CREATE TABLE IF NOT EXISTS " + TABLE_TODO_HISTORY + "(" +
                TODO_HISTORY_COLUMN_ID + " integer primary key autoincrement," +
                TODO_HISTORY_COLUMN_TODO_ID + " integer default 0," +
                TODO_HISTORY_COLUMN_TODO_DETAILS + " text," +
                TODO_HISTORY_COLUMN_CREATEDATE + " text " +
                ")";

        db.execSQL(query);


        query = "CREATE TABLE IF NOT EXISTS " + TABLE_MOTIVATIONITEM + "(" +
                MOTIVATIONITEM_COLUMN_ID + " integer primary key autoincrement," +
                MOTIVATIONITEM_COLUMN_DREAM_ID + " integer default 0," +
                MOTIVATIONITEM_COLUMN_TITLE + " text," +
                MOTIVATIONITEM_COLUMN_IMAGEBYTES + " blob, " +
                TODO_COLUMN_PERCENTAGE + " integer default 0, " +
                MOTIVATIONITEM_COLUMN_ISTRASH + " integer " +
                ")";

        db.execSQL(query);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query = " DROP TABLE IF EXISTS " + TABLE_DREAM;
        db.execSQL(query);

        query = " DROP TABLE IF EXISTS " + TABLE_TODO;
        db.execSQL(query);

        query = " DROP TABLE IF EXISTS " + TABLE_TODO_HISTORY;
        db.execSQL(query);

        query = " DROP TABLE IF EXISTS " + TABLE_MOTIVATIONITEM;
        db.execSQL(query);

        onCreate(db);

    }
}
