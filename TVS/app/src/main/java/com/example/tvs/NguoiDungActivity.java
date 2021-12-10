package com.example.tvs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tvs.dao.NguoiDungDAO;
import com.example.tvs.database.DatabaseHelper;
import com.example.tvs.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edId, edUser, edPass,edRePass, edPhone, edFullName, edMail, edAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM ADMIN");
        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edId = (EditText) findViewById(R.id.edId);
        edUser = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPassword);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edRePass = (EditText) findViewById(R.id.edRePassword);
        edMail = (EditText) findViewById(R.id.edMail);
        edAddress = (EditText) findViewById(R.id.edAddress);
    }

    public void showUsers(View view) {
        finish();
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edId.getText().toString(),edUser.getText().toString(), edPass.getText().toString(),
                edFullName.getText().toString(), edPhone.getText().toString(), edMail.getText().toString(), edAddress.getText().toString());
        try {
            if (validateForm()>0){
                if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int validateForm(){
        int check = 1;
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length()==0
                || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }


}