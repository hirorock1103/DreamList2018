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
    public Dream getDream(int dreamId){
        String query = "SELECT * FROM " + TABLE_DREAM + " WHERE " +  DREAM_COLUMN_ID + " = " + dreamId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        Dream dream = new Dream();
        while(!c.isAfterLast()){

            dream.setId(c.getInt(c.getColumnIndex(DREAM_COLUMN_ID)));
            dream.setTitle(c.getString(c.getColumnIndex(DREAM_COLUMN_TITLE)));
            dream.setImage(c.getBlob(c.getColumnIndex(DREAM_COLUMN_IMAGE)));
            dream.setDescription(c.getString(c.getColumnIndex(DREAM_COLUMN_DESCRIPTION)));
            dream.setDeadline(c.getString(c.getColumnIndex(DREAM_COLUMN_DEADLINE)));
            dream.setCreatedate(c.getString(c.getColumnIndex(DREAM_COLUMN_CREATEDATE)));

            c.moveToNext();
        }

        return dream;
    }

    public void deleteDream(int dreamId){
        String query = "DELETE FROM " + TABLE_DREAM + " WHERE " + DREAM_COLUMN_ID + " = " + dreamId;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public long updateDream(Dream dream){

        ContentValues values = new ContentValues();
        values.put(DREAM_COLUMN_TITLE, dream.getTitle());
        values.put(DREAM_COLUMN_IMAGE, dream.getImage());
        values.put(DREAM_COLUMN_DEADLINE, dream.getDeadline());

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(dream.getId())};

        long insertId = db.update(TABLE_DREAM, values, DREAM_COLUMN_ID + " = ?" , args );

        return insertId;

    }

    public List<Todo> getTodoByDreamId(int dreamId){

        List<Todo> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_TODO + " WHERE " + TODO_COLUMN_DREAMID + " = " + dreamId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){

            Todo todo = new Todo();
            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setDreamId(c.getInt(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setDescription(c.getString(c.getColumnIndex(TODO_COLUMN_DESCRIPTION)));
            todo.setDeadline(c.getString(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setIsFinished(c.getInt(c.getColumnIndex(TODO_COLUMN_ISFINISHED)));
            todo.setPercentage(c.getInt(c.getColumnIndex(TODO_COLUMN_PERCENTAGE)));
            todo.setCreatedate(c.getString(c.getColumnIndex(TODO_COLUMN_CREATEDATE)));
            list.add(todo);
            c.moveToNext();

        }

        return list;

    }

    public List<Todo> getFinishedTodoByDreamId(int dreamId){

        List<Todo> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_TODO + " WHERE " + TODO_COLUMN_DREAMID + " = " + dreamId + " AND "
                + TODO_COLUMN_ISFINISHED + " = 1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){

            Todo todo = new Todo();
            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setDreamId(c.getInt(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setDescription(c.getString(c.getColumnIndex(TODO_COLUMN_DESCRIPTION)));
            todo.setDeadline(c.getString(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setIsFinished(c.getInt(c.getColumnIndex(TODO_COLUMN_ISFINISHED)));
            todo.setPercentage(c.getInt(c.getColumnIndex(TODO_COLUMN_PERCENTAGE)));
            todo.setCreatedate(c.getString(c.getColumnIndex(TODO_COLUMN_CREATEDATE)));
            list.add(todo);
            c.moveToNext();

        }

        return list;

    }

    public List<Todo> getNotFinishedTodoByDreamId(int dreamId){

        List<Todo> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_TODO + " WHERE " + TODO_COLUMN_DREAMID + " = " + dreamId
                +  " AND " + TODO_COLUMN_ISFINISHED + " = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){

            Todo todo = new Todo();
            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setDreamId(c.getInt(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setDescription(c.getString(c.getColumnIndex(TODO_COLUMN_DESCRIPTION)));
            todo.setDeadline(c.getString(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setIsFinished(c.getInt(c.getColumnIndex(TODO_COLUMN_ISFINISHED)));
            todo.setPercentage(c.getInt(c.getColumnIndex(TODO_COLUMN_PERCENTAGE)));
            todo.setCreatedate(c.getString(c.getColumnIndex(TODO_COLUMN_CREATEDATE)));
            list.add(todo);
            c.moveToNext();

        }

        return list;

    }

    /**
     * @param
     * @return
     */
    public long addTodo(Todo todo){
        ContentValues values = new ContentValues();
        values.put(TODO_COLUMN_TITLE, todo.getTitle());
        values.put(TODO_COLUMN_DREAMID, todo.getDreamId());
        values.put(TODO_COLUMN_CREATEDATE,Common.formatDate(new Date(), Common.DB_DATE_FORMAT));

        SQLiteDatabase db = getWritableDatabase();
        long insertID = db.insert(TABLE_TODO, null, values);

        return insertID;
    }

    public void deleteTodo(int id){

        String query = "DELETE FROM "+ TABLE_TODO + " WHERE " + TODO_COLUMN_ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    public long updateTodo(Todo todo){

        ContentValues values = new ContentValues();

        values.put(TODO_COLUMN_ISFINISHED, todo.getIsFinished());
        values.put(TODO_COLUMN_TITLE, todo.getTitle());

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(todo.getId())};

        long insertId = db.update(TABLE_TODO, values, TODO_COLUMN_ID + " = ?", args);

        Common.log("insertId:" + insertId);

        return insertId;

    }

    public Todo getTodoById(int id){

        List<Todo> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_TODO + " WHERE " + TODO_COLUMN_ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        Todo todo = new Todo();
        while(!c.isAfterLast()){

            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setDreamId(c.getInt(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setDescription(c.getString(c.getColumnIndex(TODO_COLUMN_DESCRIPTION)));
            todo.setDeadline(c.getString(c.getColumnIndex(TODO_COLUMN_DREAMID)));
            todo.setIsFinished(c.getInt(c.getColumnIndex(TODO_COLUMN_ISFINISHED)));
            todo.setPercentage(c.getInt(c.getColumnIndex(TODO_COLUMN_PERCENTAGE)));
            todo.setCreatedate(c.getString(c.getColumnIndex(TODO_COLUMN_CREATEDATE)));

            c.moveToNext();

        }

        return todo;

    }


    public long addDream(Dream dream){

        ContentValues values = new ContentValues();

        values.put(DREAM_COLUMN_TITLE, dream.getTitle());
        values.put(DREAM_COLUMN_DESCRIPTION,dream.getDescription());
        values.put(DREAM_COLUMN_IMAGE, dream.getImage());
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
            dream.setImage(c.getBlob(c.getColumnIndex(DREAM_COLUMN_IMAGE)));
            dream.setDescription(c.getString(c.getColumnIndex(DREAM_COLUMN_DESCRIPTION)));
            dream.setDeadline(c.getString(c.getColumnIndex(DREAM_COLUMN_DEADLINE)));
            dream.setCreatedate(c.getString(c.getColumnIndex(DREAM_COLUMN_CREATEDATE)));

            list.add(dream);

            c.moveToNext();
        }

        return list;

    }


}
