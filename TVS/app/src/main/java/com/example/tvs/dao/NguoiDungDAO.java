package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung(idNguoidung text , username text primary key,password text,name text, phone text, mail text, address text);";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("idNguoiDung",nd.getIdNguoiDung());
        values.put("username",nd.getUsername());
        values.put("password",nd.getPassword());
        values.put("phone",nd.getPhone());
        values.put("name",nd.getName());
        values.put("mail",nd.getMail());
        values.put("address",nd.getAddress());
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
    public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            NguoiDung ee = new NguoiDung();
            ee.setIdNguoiDung(c.getString(0));
            ee.setUsername(c.getString(1));
            ee.setPassword(c.getString(2));
            ee.setPhone(c.getString(3));
            ee.setName(c.getString(4));
            ee.setMail(c.getString(5));
            ee.setAddress(c.getString(6));
            dsNguoiDung.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;

    }
    //update
    public int updateNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("idNguoiDung",nd.getIdNguoiDung());
        values.put("username",nd.getUsername());
        values.put("password",nd.getPassword());
        values.put("phone",nd.getPhone());
        values.put("name",nd.getName());
        values.put("mail",nd.getMail());
        values.put("address",nd.getAddress());
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{nd.getUsername()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int changePasswordNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUsername());
        values.put("password",nd.getPassword());
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{nd.getUsername()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int updateInfoNguoiDung(String username, String name, String phone, String email){
        ContentValues values = new ContentValues();
        values.put("hoten",name);
        values.put("phone",phone);
        values.put("mail", email);
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteNguoiDungByID(String username){
        int result = db.delete(TABLE_NAME,"username=?",new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }
    //check login
    public int checkLogin(String username, String password){
        int result = db.delete(TABLE_NAME,"username=? AND password=?",new String[]{username,password});
        if (result == 0)
            return -1;
        return 1;
    }

}
