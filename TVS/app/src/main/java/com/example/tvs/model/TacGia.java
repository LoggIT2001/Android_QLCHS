package com.example.tvs.model;

public class TacGia {
    private String tenTacGia;
    private String idTacGia;

    public TacGia(){

    }
    public TacGia(String idTacGia, String tenTacGia){
        this.idTacGia = idTacGia;
        this.tenTacGia = tenTacGia;
    }
    public String getIdTacGia(){
        return this.idTacGia;
    }
    public void setIdTacGia(String idTacGia) {
        this.idTacGia = idTacGia;
    }
    public String getTenTacGia() {
        return tenTacGia;
    }
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
    @Override
    public String toString() {
        return idTacGia + "|" + tenTacGia;
    }
}
