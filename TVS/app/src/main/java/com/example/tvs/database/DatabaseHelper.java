package com.example.tvs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tvs.dao.HoaDonChiTietDAO;
import com.example.tvs.dao.HoaDonDAO;
import com.example.tvs.dao.SachDAO;
import com.example.tvs.dao.TacGiaDAO;
import com.example.tvs.dao.TheLoaiDAO;
import com.example.tvs.dao.NguoiDungDAO;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "dbBookManager.db";
    public static final int VERSION = 1;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(TacGiaDAO.SQL_TAC_GIA);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("Drop table if exists " + TheLoaiDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + TacGiaDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + SachDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonChiTietDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + NguoiDungDAO.TABLE_NAME) ;
        onCreate(db);
    }
}
