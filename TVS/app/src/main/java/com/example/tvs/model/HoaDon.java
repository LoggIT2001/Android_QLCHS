package com.example.tvs.model;

import java.util.Date;

public class HoaDon {
    private String idHoaDon;
    private Date ngayMua;

    public HoaDon(){

    }
    public HoaDon(String maHoaDon, Date ngayMua){
        this.idHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
    public String getIdHoaDon(){
        return this.idHoaDon;
    }
    public void setIdHoaDon(String maHoaDon){
        this.idHoaDon = maHoaDon;
    }
    public Date getNgayMua(){
        return this.ngayMua;
    }
    public void setNgayMua(Date ngayMua){
        this.ngayMua = ngayMua;
    }
    @Override
    public String toString(){
        return "HoaDon("+" MaHoaDon: " + this.idHoaDon + " NgayMua: " + this.ngayMua + " )";
    }
}
