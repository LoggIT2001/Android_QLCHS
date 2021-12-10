package com.example.tvs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tvs.dao.TacGiaDAO;
import com.example.tvs.dao.TheLoaiDAO;
import com.example.tvs.model.TacGia;
import com.example.tvs.model.TheLoai;

public class TacGiaActivity extends AppCompatActivity {
    EditText edIdTacGia, edTenTacGia;
    Button btnAdd, btnCancel, btnShow;
    TacGiaDAO tacGiaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TÁC GIẢ");
        setContentView(R.layout.activity_tac_gia);
        edIdTacGia = (EditText) findViewById(R.id.edIdTacGia);
        edTenTacGia = (EditText) findViewById(R.id.edTenTacGia);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edIdTacGia.setText(b.getString("MATACGIA"));
            edTenTacGia.setText(b.getString("TENTACGIA"));
        }
    }

    public void showTheLoai(View view) {
        finish();
    }

    public void addTacGia(View view) {
        tacGiaDAO = new TacGiaDAO(TacGiaActivity.this);

        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                TacGia tacGia = new TacGia(edIdTacGia.getText().toString(), edTenTacGia.getText().toString());
                if (tacGiaDAO.inserTacGia(tacGia) > 0) {
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
        if (edIdTacGia.getText().length() == 0 || edTenTacGia.getText().length() == 0) {
            check = -1;
        }
        return check;
    }

}