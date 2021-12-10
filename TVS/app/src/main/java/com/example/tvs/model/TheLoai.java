package com.example.tvs.model;

public class TheLoai {
    private String idTheloai;
    private String tenTheLoai;

    public TheLoai(){

    }
    public TheLoai(String idTheloai, String tenTheLoai){
        this.idTheloai = idTheloai;
        this.tenTheLoai = tenTheLoai;
    }

    public String getIdTheLoai() {
        return idTheloai;
    }

    public void setIdTheLoai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    @Override
    public String toString() {
        return idTheloai + "|" + tenTheLoai;
    }
}
