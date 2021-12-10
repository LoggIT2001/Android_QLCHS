package com.example.tvs.model;

public class HoaDonChiTiet {
    private int idHDCT;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuongMua;
    private String khachHang;
    private String sdt;

    public HoaDonChiTiet(){

    }
    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, Sach sach, int soLuongMua, String khachHang, String sdt){
        this.idHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
        this.khachHang = khachHang;
        this.sdt = sdt;
    }
    public int getIdHDCT(){
        return this.idHDCT;
    }
    public void setIdHDCT(int maHDCT){
        this.idHDCT = maHDCT;
    }
    public HoaDon getHoaDon(){
        return this.hoaDon;
    }
    public void setHoaDon(HoaDon hoaDon){
        this.hoaDon = hoaDon;
    }
    public Sach getSach(){
        return this.sach;
    }
    public void setSach(Sach sach){
        this.sach = sach;
    }
    public int getSoLuongMua(){
        return this.soLuongMua;
    }
    public void setSoLuongMua(int soLuongMua){
        this.soLuongMua = soLuongMua;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "idHDCT=" + idHDCT +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuongMua=" + soLuongMua +
                ", khachHang='" + khachHang  +
                ", sdt='" + sdt +
                '}';
    }
}
