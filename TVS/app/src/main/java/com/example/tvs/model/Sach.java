package com.example.tvs.model;

public class Sach {
    private String idSach;
    private String idTheLoai;
    private String idTacGia;
    private String name;
    private String NXB;
    private double giaBia;
    private int soLuong;

    public Sach(){

    }
    public Sach(String maSach, String maTheLoai, String tacGia, String tenSach, String NXB, double giaBia,int soLuong){
        this.idSach = maSach;
        this.idTheLoai = maTheLoai;
        this.idTacGia = tacGia;
        this.name = tenSach;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public String getIdSach(){
        return this.idSach;
    }
    public void setIdSach(String maSach){
        this.idSach = maSach;
    }
    public String getIdTheLoai(){
        return this.idTheLoai;
    }
    public void setIdTheLoai(String maTheLoai){
        this.idTheLoai = maTheLoai;
    }
    public String getTenSach(){
        return name;
    }
    public void setTenSach(String tenSach){
        this.name = tenSach;
    }
    public String getIdTacGia() {
        return this.idTacGia;
    }
    public void setIdTacGia(String tacGia){
        this.idTacGia = tacGia;
    }
    public String getNXB(){
        return this.NXB;
    }
    public void setNXB(String NXB){
        this.NXB = NXB;
    }
    public double getGiaBia() {
        return giaBia;
    }
    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }
    public int getSoLuong(){
        return this.soLuong;
    }
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    @Override
    public String toString(){
        return "Sach( " + "maSach: " + this.idSach +
                " maTheLoai: " + this.idTheLoai +
                " tenSach: " + this.name +
                " tacGia: " + this.idTacGia +
                " NXB: " + this.NXB +
                " giaBia: " + this.giaBia +
                " soLuong: " + this.soLuong + " )";
    }
}
