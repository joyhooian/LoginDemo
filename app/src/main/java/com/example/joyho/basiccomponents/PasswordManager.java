package com.example.joyho.basiccomponents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PasswordManager extends SQLiteOpenHelper{
    private static final String TABLE_NAME = "log_info";
    private static final String ID_FILED = "_id";
    private static final String EMAIL_ADD = "email_add";
    private static final String PWD = "pwd";

    PasswordManager(Context context){
        super(context, "log_info_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("db", "onCreate");
        String sql = "CREATE TABLE " + TABLE_NAME +" (" + ID_FILED + " INTEGER PRIMARY KEY, " + EMAIL_ADD + " VARCHAR(50), " + PWD + " VARCHAR(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        Log.d("db", "onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addLogInfo(LogInfo logInfo){
        Log.d("db","addLogInfo");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_ADD, logInfo.getEmailAdd());
        values.put(PWD, logInfo.getPwd());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String find(String emailADD){
        String pwd = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            if(emailADD.equals(new LogInfo(cursor.getString(cursor.getColumnIndex(EMAIL_ADD)), null).getEmailAdd())){
                LogInfo logInfo = new LogInfo(null, cursor.getString(cursor.getColumnIndex(PWD)));
                pwd =  logInfo.getPwd();
            }
        }
        cursor.close();
        return pwd;
    }
}
