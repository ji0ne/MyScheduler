/*
* 참고: https://popcorn16.tistory.com/76
* */

package com.example.swt_cal_sql_ex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Cal extends SQLiteOpenHelper {
    public DB_Cal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // DB 생성
        String sql = "CREATE TABLE if not exists mytable ("
                + "_id integer primary key autoincrement,"
                + "txt text);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 삭제
        String sql = "DROP TABLE if exists mytable";

        db.execSQL(sql);
        onCreate(db);
    }
}