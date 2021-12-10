package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH ="CREATE TABLE Sach(idSach text primary key, idTheLoai text, idTacGia text, tenSach text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "SachDAO";
    public SachDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("idSach",s.getIdSach());
        values.put("idTheLoai",s.getIdTheLoai());
        values.put("idTacGia",s.getIdTacGia());
        values.put("tenSach",s.getTenSach());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong",s.getSoLuong());
        if (checkPrimaryKey(s.getIdSach())){
            int result = db.update(TABLE_NAME,values,"idSach=?", new String[]{s.getIdSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, values)==-1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }
    //getAll
    public List<Sach> getAllSach(){
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Sach s = new Sach();
            s.setIdSach(c.getString(0));
            s.setIdTheLoai(c.getString(1));
            s.setIdTacGia(c.getString(2));
            s.setTenSach(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;

    }
    //update
    public int updateSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("idSach",s.getIdSach());
        values.put("idTheLoai",s.getIdTheLoai());
        values.put("idTacGia",s.getIdTacGia());
        values.put("tenSach",s.getTenSach());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong",s.getSoLuong());
        int result = db.update(TABLE_NAME,values,"idSach=?", new String[]{s.getIdSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteSachByID(String idSach){
        int result = db.delete(TABLE_NAME,"idSach=?",new String[]{idSach});
        if (result == 0)
            return -1;
        return 1;
    }
    //check khoa chinh
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"idSach"};
        //WHERE clause
        String selection = "idSach=?";
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
    //check sach
    public Sach checkBook(String strPrimaryKey){
        Sach s = new Sach();
        //SELECT
        String[] columns = {"idSach"};
        //WHERE clause
        String selection = "idSach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();

            while (c.isAfterLast()==false){
                s.setIdSach(c.getString(0));
                s.setIdTheLoai(c.getString(1));
                s.setIdTacGia(c.getString(2));
                s.setTenSach(c.getString(3));
                s.setNXB(c.getString(4));
                s.setGiaBia(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//=====",s.toString());
                break;
            }
            c.close();
            return s;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //getAll -tim theo id sach
    public Sach getSachByID(String idSach){
        Sach s = null;
        //WHERE clause
        String selection = "idsach=?";
        //WHERE clause arguments
        String[] selectionArgs = {idSach};
        Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setIdSach(c.getString(0));
            s.setIdTheLoai(c.getString(1));
            s.setIdTacGia(c.getString(2));
            s.setTenSach(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;

        }
        c.close();
        return s;
    }

}
