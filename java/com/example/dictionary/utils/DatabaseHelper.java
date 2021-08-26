package com.example.dictionary.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "dict.sqlite";
    public static final String DBLOCATION = "data/data/com.example.dictionary/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context)
    {
        super(context, DBNAME, null, 1);
        this.mContext = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
    public void openDatabase()
    {
        new Thread(new Runnable() {
            public void run() {
                String dbPath=mContext.getDatabasePath(DBNAME).getPath();
                mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
            }
        }).start();

    }
    public Cursor getListWord(String wordSearch)
    {
       Cursor cursor=null;
        try{
          openDatabase();
           String[] args= {"%"+wordSearch +"%"};
           cursor=mDatabase.rawQuery("Select * from WORDS Where word like ? ",args);
          }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cursor;
    }
}

