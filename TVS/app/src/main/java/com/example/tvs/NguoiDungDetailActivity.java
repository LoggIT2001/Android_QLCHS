package com.example.tvs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tvs.dao.NguoiDungDAO;
import com.example.tvs.model.NguoiDung;

public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edFullName, edPhone, edAddress;
    NguoiDungDAO nguoiDungDAO;
    String username, fullname, phone, address;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setTitle("CHI TIET ADMIN");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edAddress = (EditText) findViewById(R.id.edAddress);
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        address = b.getString("MAIL");
        username = b.getString("USERNAME");
        edFullName.setText(fullname);
        edPhone.setText(phone);
        edAddress.setText(address);
    }
    public void updateUser(View view){
        if (nguoiDungDAO.updateInfoNguoiDung(username,edFullName.getText().toString(), edPhone.getText().toString(), edAddress.getText().toString())>0){
            Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
        }
    }
    public void Huy(View view){
        finish();
    }
}
