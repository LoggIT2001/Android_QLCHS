package com.example.tvs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tvs.dao.SachDAO;
import com.example.tvs.dao.TheLoaiDAO;
import com.example.tvs.dao.TacGiaDAO;
import com.example.tvs.model.Sach;
import com.example.tvs.model.TheLoai;
import com.example.tvs.model.TacGia;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    TacGiaDAO tacGiaDAO;
    Spinner spnTheLoai;
    Spinner spnTacGia;
    EditText edIdSach, edTenSach, edNXB, edGiaBia, edSoLuong;
    String idTheLoai ="";
    String idTacGia="";
    List<TheLoai> listTheLoai = new ArrayList<>();
    List<TacGia> listTacGia = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        setTitle("Sách");
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        spnTacGia = (Spinner) findViewById(R.id.spnTacGia);
        getTacGia();
        edIdSach = (EditText) findViewById(R.id.edIdSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText)findViewById(R.id.edSoLuong);
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getIdTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnTacGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idTacGia = listTacGia.get(spnTacGia.getSelectedItemPosition()).getIdTacGia();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edIdSach.setText(b.getString("MASACH"));
            String idTheLoai = b.getString("MATHELOAI");
            String idTacGia = b.getString("MATACGIA");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(idTheLoai));
            spnTacGia.setSelection(checkPositionTacGia(idTacGia));
        }
    }
    public void showSpinner(View view){
        sachDAO = new SachDAO(SachActivity.this);
        sachDAO.getAllSach();
    }
    public void getTheLoai(){
        theLoaiDAO = new TheLoaiDAO(SachActivity.this);

        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }
    public void getTacGia(){
        tacGiaDAO = new TacGiaDAO(SachActivity.this);

        listTacGia = tacGiaDAO.getAllTacGia();
        ArrayAdapter<TacGia> dataAdapter = new ArrayAdapter<TacGia>(this,
                android.R.layout.simple_spinner_item, listTacGia);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTacGia.setAdapter(dataAdapter);
    }
    public void addBook(View view){
        sachDAO = new SachDAO(SachActivity.this);
        Sach sach = new Sach(edIdSach.getText().toString(),idTheLoai,idTacGia,edTenSach.getText().toString(),
                edNXB.getText().toString(),
                Double.parseDouble(edGiaBia.getText().toString()),Integer.parseInt(edSoLuong.getText().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void showBook(View view){
        finish();
    }
    public int checkPositionTheLoai(String strTheLoai){
        for (int i = 0; i <listTheLoai.size(); i++){
            if (strTheLoai!=null && strTheLoai.equals(listTheLoai.get(i).getIdTheLoai())==true){
                return i;
            }
        }
        return 0;
    }
    public int checkPositionTacGia(String strTacGia){
        for (int i = 0; i <listTacGia.size(); i++){
            if (strTacGia!=null && strTacGia.equals(listTacGia.get(i).getIdTacGia())==true){
                return i;
            }
        }
        return 0;
    }
}