package com.example.tvs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tvs.database.DatabaseHelper;
import android.util.Log;

import com.example.tvs.model.HoaDonChiTiet;
import com.example.tvs.model.HoaDon;
import com.example.tvs.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet(idHDCT INTEGER PRIMARY KEY AUTOINCREMENT, idHoaDon text NOT NULL, idSach text NOT NULL, soLuong INTEGER, khachHang text NOT NULL, sdt text NOT NULL);";
    public static final String TAG = "HoaDonChiTiet";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonChiTietDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    // insert
    public int inserHoaDonChiTiet(HoaDonChiTiet dh){
        ContentValues values = new ContentValues();
        values.put("idHoaDon", dh.getHoaDon().getIdHoaDon());
        values.put("idSach", dh.getSach().getIdSach());
        values.put("soLuong", dh.getSoLuongMua());
        values.put("khachHang", dh.getKhachHang());
        values.put("sdt", dh.getSdt());
        try{
            if(db.insert(TABLE_NAME,null,values)==-1) {
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getALL
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(){
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT idHDCT, HoaDon.idHoaDon, HoaDon.ngayMua, "+
                "Sach.idSach, Sach.idTheLoai, Sach.idTacGia, Sach.TenSach, Sach.NXB, Sach.giaBia, Sach.soLuong, " +
                "HoaDonChiTiet.soLuong, HoaDonChiTiet.khachHang, HoaDonChiTiet.sdt FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.idHoaDon = HoaDon.idHoaDon INNER JOIN Sach on Sach.idSach = HoaDonChiTiet.idSach;";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast() == false) {
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setIdHDCT(c.getInt(0));
                ee.setHoaDon(new HoaDon(c.getString(1), sdf.parse(c.getString(2))));
                ee.setSach(new Sach(c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getDouble(8), c.getInt(9)));
                ee.setSoLuongMua(c.getInt(10));
                ee.setKhachHang(c.getString(11));
                ee.setSdt(c.getString(12));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====", ee.toString());
                c.moveToNext();
            }
            c.close();
        }catch (Exception e){
            Log.d(TAG,e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //getAll
    public List<HoaDonChiTiet> getAllHoaDonChiTietByID(String idHoaDon){
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT idHDCT, HoaDon.idHoaDon, HoaDon.ngayMua, " +
                "Sach.idSach, Sach.idTheLoai, Sach.idTacGia, Sach.tenSach, Sach.NXB, Sach.giaBia, " +
                "Sach.soLuong, HoaDonChiTiet.soLuong, HoaDonChiTiet.khachHang, HoaDonChiTiet.sdt FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.idHoaDon = HoaDon.idHoaDon INNER JOIN Sach on " +
                "Sach.idSach = HoaDonChiTiet.idSach where HoaDonChiTiet.idHoaDon='"+idHoaDon+"';";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setIdHDCT(c.getInt(0));
                ee.setHoaDon(new HoaDon(c.getString(1),sdf.parse(c.getString(2))));
                ee.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7),c.getDouble(8),c.getInt(9)));
                ee.setSoLuongMua(c.getInt(10));
                ee.setKhachHang(c.getString(11));
                ee.setSdt(c.getString(12));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====",ee.toString());
                c.moveToNext();
            }
            c.close();

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //update
    public int updateHoaDonChiTiet(HoaDonChiTiet hd){
        ContentValues values = new ContentValues();
        values.put("idHDCT",hd.getIdHDCT());
        values.put("idHoaDon",hd.getHoaDon().getIdHoaDon());
        values.put("idSach",hd.getSach().getIdSach());
        values.put("soLuong",hd.getSoLuongMua());
        values.put("khachHang", hd.getKhachHang());
        values.put("sdt", hd.getSdt());
        int result = db.update(TABLE_NAME,values,"idHDCT=?", new String[]{String.valueOf(hd.getIdHDCT())});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteHoaDonChiTietByID(String idHDCT){
        int result = db.delete(TABLE_NAME,"idHDCT=?",new String[]{idHDCT});
        if (result == 0)
            return -1;
        return 1;
    }
    //check
    public boolean checkHoaDon(String idHoaDon){
        //SELECT
        String[] columns = {"idHoaDon"};
        //WHERE clause
        String selection = "idHoaDon=?";
        //WHERE clause arguments
        String[] selectionArgs = {idHoaDon};
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
    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.idHoaDon = HoaDonChiTiet.idHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.idSach = Sach.idSach where HoaDon.ngayMua = date('now') " +
                "GROUP BY HoaDonChiTiet.idSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;

    }

    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.idHoaDon = HoaDonChiTiet.idHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.idSach = Sach.idSach where strftime('%m',HoaDon.ngayMua) = " +
                "strftime('%m','now') GROUP BY HoaDonChiTiet.idSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;

    }

    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.idHoaDon = HoaDonChiTiet.idHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.idSach = Sach.idSach where strftime('%Y',HoaDon.ngayMua)" +
                " = strftime('%Y','now') GROUP BY HoaDonChiTiet.idSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;

    }
}
