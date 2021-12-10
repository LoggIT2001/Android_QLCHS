package com.example.tvs.model;

public class NguoiDung {
    private String idNguoiDung;
    private String username;
    private String password;
    private String phone;
    private String name;
    private String mail;
    private String address;

    public NguoiDung(){

    }
    public NguoiDung(String id,String username, String password, String phone, String name, String address, String mail){
        this.idNguoiDung = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.mail = mail;
        this.address = address;
    }
    public String getIdNguoiDung(){
        return this.idNguoiDung;
    }
    public void setIdNguoiDung(String id){
        this.idNguoiDung = id;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getMail(){
        return this.mail;
    }
    public void setMail(String mail){
        this.mail = mail;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String string){
        this.address = address;
    }
    @Override
    public String toString(){
        return "Nguoidung( " + "username: " + this.username +
                " password: " + this.password +
                " name: " + this.name +
                " phone: " + this.phone +
                " mail: " + this.mail +
                " address: " + this.address + " )";
    }
}
