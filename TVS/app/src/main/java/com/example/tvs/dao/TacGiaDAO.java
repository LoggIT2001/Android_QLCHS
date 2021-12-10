package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.TacGia;

import java.util.ArrayList;
import java.util.List;

public class TacGiaDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "TacGia";
    public static final String SQL_TAC_GIA ="CREATE TABLE TacGia (idtacgia text primary key, tentacgia text);";
    public static final String TAG = "TacGiaDAO";
    public TacGiaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserTacGia(TacGia tacGia){
        ContentValues values = new ContentValues();
        values.put("idtacgia",tacGia.getIdTacGia());
        values.put("tentacgia",tacGia.getTenTacGia());
        if (checkPrimaryKey(tacGia.getIdTacGia())){
            int result = db.update(TABLE_NAME,values,"idtacgia=?", new String[]{tacGia.getIdTacGia()});
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
    public List<TacGia> getAllTacGia(){
        List<TacGia> dsTacGia = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            TacGia ee = new TacGia();
            ee.setIdTacGia(c.getString(0));
            ee.setTenTacGia(c.getString(1));
            dsTacGia.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTacGia;
    }
    //update
    public int updateTacGia(TacGia tacGia){
        ContentValues values = new ContentValues();
        values.put("idtacgia",tacGia.getIdTacGia());
        values.put("tentacgia",tacGia.getTenTacGia());
        int result = db.update(TABLE_NAME,values,"idtacgia=?", new String[]{tacGia.getIdTacGia()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteTacGiaByID(String idTacGia){
        int result = db.delete(TABLE_NAME,"idTacGia=?",new String[]{idTacGia});
        if (result == 0)
            return -1;
        return 1;
    }
    //check
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"idtacgia"};
        //WHERE clause
        String selection = "idtacgia=?";
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
