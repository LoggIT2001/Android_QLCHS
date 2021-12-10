package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON ="CREATE TABLE HoaDon (idhoadon text primary key, ngaymua date);";
    public static final String TAG = "HoaDonDAO";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("idhoadon",hd.getIdHoaDon());
        values.put("ngaymua",sdf.format(hd.getNgayMua()));
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getAll
    public List<HoaDon> getAllHoaDon() throws ParseException {
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            HoaDon ee = new HoaDon();
            ee.setIdHoaDon(c.getString(0));
            ee.setNgayMua(sdf.parse(c.getString(1)));
            dsHoaDon.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsHoaDon;
    }
    //update
    public int updateHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("idhoadon",hd.getIdHoaDon());
        values.put("ngaymua",hd.getNgayMua().toString());
        int result = db.update(TABLE_NAME,values,"idhoadon=?", new String[]{hd.getIdHoaDon()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteHoaDonByID(String idhoadon){
        int result = db.delete(TABLE_NAME,"idhoadon=?",new String[]{idhoadon});
        if (result == 0)
            return -1;
        return 1;
    }
}
