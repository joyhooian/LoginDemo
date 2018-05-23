package com.example.joyho.basiccomponents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AlarmDataManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "alarm_data";
    public static final String ID_FIELD = "_id";
    public static final String TIME = "time";
    public static final String INDEX_FIELD = "_index";
    public static final String VALUE = "_value";

    AlarmDataManager (Context context){
        super(context, "alarm_data_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + ID_FIELD + " INTEGER PRIMARY KEY, " + TIME + " TEXT, " + INDEX_FIELD + " TEXT, " + VALUE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAlarmData(AlarmData alarmData){
        Log.d("db","addAlarmData");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, alarmData.getAlarmTime());
        values.put(INDEX_FIELD, alarmData.getAlarmIndex());
        values.put(VALUE, alarmData.getAlarmValue());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAlarmDataCursor(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery,null);
    }
}
