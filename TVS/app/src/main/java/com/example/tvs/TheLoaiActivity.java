package com.example.tvs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tvs.dao.TheLoaiDAO;
import com.example.tvs.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {

    EditText edIdTheLoai, edTenTheLoai;
    Button btnAdd, btnCancel, btnShow;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);
        edIdTheLoai = (EditText) findViewById(R.id.edIdTheLoai);
        edTenTheLoai = (EditText) findViewById(R.id.edTenTheLoai);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edIdTheLoai.setText(b.getString("MATHELOAI"));
            edTenTheLoai.setText(b.getString("TENTHELOAI"));
        }
    }

    public void showTheLoai(View view) {
        finish();
    }

    public void addTheLoai(View view) {
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);

        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                TheLoai theLoai = new TheLoai(edIdTheLoai.getText().toString(), edTenTheLoai.getText().toString());
                if (theLoaiDAO.inserTheLoai(theLoai) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validation() {
        int check = 1;
        if (edIdTheLoai.getText().length() == 0 || edTenTheLoai.getText().length() == 0) {
            check = -1;
        }
        return check;
    }
}