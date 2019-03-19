package com.example.cbocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "menuItems.db";
    public static final String MENU_TABLE = "menuTable";
    public static final String SIDE_TABLE = "sideTable";

    public static final String MENU_1 = "foodID";
    public static final String MENU_2 = "foodName";
    public static final String MENU_3 = "foodAvail";
    public static final String MENU_4 = "numSides";
    public static final String MENU_5 = "menuType";
    /*
    * Maybe have the picture of the items later.
    */

    public static final String SIDE_1 = "sideID";
    public static final String SIDE_2 = "sideName";
    public static final String SIDE_3 = "sideAvail";

    public MenuDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MENU_TABLE + " (foodID INTEGER PRIMARY KEY AUTOINCREMENT, foodName TEXT, foodAvail TEXT, numSides INT, menuType TEXT)");
        db.execSQL("create table " + SIDE_TABLE + " (sideID INTEGER PRIMARY KEY AUTOINCREMENT, sideName TEXT, sideAvail TEXT)");
    }
    /* Only call this if you want to delete everything in the database and start over. */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MENU_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SIDE_TABLE);
        onCreate(db);
    }
    public Cursor getCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor crs = db.rawQuery("select * from " + MENU_TABLE, null);
        return crs;
    }

    public Cursor getFFCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor crs = db.rawQuery("select * from " + MENU_TABLE + " where " + MENU_5 + " like 'Fancy Fixin'", null);
        return crs;
    }

    public Cursor getSidesCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor crs = db.rawQuery("select * from " + SIDE_TABLE, null);
        return crs;
    }
    public boolean addMenuItem(String name, String avail, int numSides, String menuType){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(MENU_2, name);
        cV.put(MENU_3, avail);
        cV.put(MENU_4, numSides);
        cV.put(MENU_5, menuType);

        return db.insert(MENU_TABLE, null, cV) != -1;
    }
    public boolean addSideItem(String name, String avail){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(SIDE_2, name);
        cV.put(SIDE_3, avail);

        return db.insert(SIDE_TABLE, null, cV) != -1;
    }
}
