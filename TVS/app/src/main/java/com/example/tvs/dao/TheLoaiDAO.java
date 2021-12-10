package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI ="CREATE TABLE TheLoai (idtheloai text primary key, " +
            "tentheloai text);";
    public static final String TAG = "TheLoaiDAO";
    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("idtheloai",theLoai.getIdTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        if (checkPrimaryKey(theLoai.getIdTheLoai())){
            int result = db.update(TABLE_NAME,values,"idtheloai=?", new String[]{theLoai.getIdTheLoai()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }
    //getAll
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            TheLoai ee = new TheLoai();
            ee.setIdTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            dsTheLoai.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }
    //update
    public int updateTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("idtheloai",theLoai.getIdTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        int result = db.update(TABLE_NAME,values,"idtheloai=?", new String[]{theLoai.getIdTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteTheLoaiByID(String idtheloai){
        int result = db.delete(TABLE_NAME,"idtheloai=?",new String[]{idtheloai});
        if (result == 0)
            return -1;
        return 1;
    }
    //check
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"idtheloai"};
        //WHERE clause
        String selection = "idtheloai=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
