package com.management.agenda.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.management.agenda.models.Evento;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "evento.db";
    private static final String TABLE_NAME = "tbl_evento";

    //database columns
    private static final String COL_1 = "id";
    private static final String COL_2 = "titulo";
    private static final String COL_3 = "data";
    private static final String COL_4 = "descrição";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, data TEXT, descrição TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insert(String title, String date, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, desc);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result != -1){
            return true;
        }
        return false;
    }

    public List<Evento> getAllData(){
        List<Evento> eventos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        while (c.moveToNext()) {
            eventos.add(new Evento(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3))
            );
        }
        c.close();

        return (!eventos.isEmpty()) ? eventos: new ArrayList<Evento>();
    }

    public boolean update(int id, String title, String date, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, title);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, desc);

        int result = db.update(TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(id)});

        return result > 0;
    }

    public boolean delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});

        return i > 0;
    }
}