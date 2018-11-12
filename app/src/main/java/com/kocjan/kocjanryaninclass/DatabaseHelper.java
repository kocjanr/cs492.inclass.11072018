package com.kocjan.kocjanryaninclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "Students";
    public static final String ID = "id";
    public static final String FIRST = "first";
    public static final String LAST = "last";
    public static final String GRADE = "grade";

    public static final String DATABASE_NAME = "studentBase.db";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQLiteDatabase db = this.getWritableDatabase();
        sqLiteDatabase.execSQL("CREATE TABLE " + NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "FIRST TEXT, LAST TEXT,GRADE INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String first, String last, String grade){
        SQLiteDatabase db = this .getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST,first);
        values.put(LAST,last);
        values.put(GRADE,grade);
        long result = db.insert(NAME,null,values);

        if(result==-1 ){
            return false;
        } else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NAME, null);
        return cursor;
    }
}
