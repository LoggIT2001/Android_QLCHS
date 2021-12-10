package com.example.tvs;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String strUserName, strPassword;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setTitle("CỬA HÀNG SÁCH");
        setContentView(R.layout.activity_main);
    }

    public void viewNguoiDung(View v){
        Intent intent = new Intent(MainActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }
    public void viewTheLoai(View v){
        Intent intent = new Intent(MainActivity.this,ListTheLoaiActivity.class);
        startActivity(intent);
    }
    public void viewListBookActivity(View v){
        Intent intent = new Intent(MainActivity.this,ListSachActivity.class);
        startActivity(intent);
    }
    public void viewListHoaDonActivity(View v){
        Intent intent = new Intent(MainActivity.this,ListHoaDonActivity.class);
        startActivity(intent);
    }
    public void viewTacGia(View v){
        Intent intent = new Intent(MainActivity.this, ListTacGiaActivity.class);
        startActivity(intent);
    }
    public void viewThongKe(View v){
        Intent intent = new Intent(MainActivity.this, ThongKeActivity.class);
        startActivity(intent);
    }
}
