package com.example.tvs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvs.adapter.CartAdapter;
import com.example.tvs.dao.HoaDonChiTietDAO;
import com.example.tvs.dao.SachDAO;
import com.example.tvs.dao.HoaDonDAO;
import com.example.tvs.model.HoaDon;
import com.example.tvs.model.HoaDonChiTiet;
import com.example.tvs.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edIdSach, edIdHoaDon, edSoLuong, edKhachHang, edSDT;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO sachDAO;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT HOÁ ĐƠN");
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edIdSach = (EditText) findViewById(R.id.edIdSach);
        edIdHoaDon = (EditText) findViewById(R.id.edIdHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        edKhachHang = (EditText) findViewById(R.id.edKhachHang);
        edSDT = (EditText) findViewById(R.id.edSDT);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);

        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edIdHoaDon.setText(b.getString("MAHOADON"));
        }
    }
    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        sachDAO = new SachDAO(HoaDonChiTietActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Sach sach = sachDAO.getSachByID(edIdSach.getText().toString());
                if (sach!=null){
                    int pos = checkIdSach(dsHDCT,edIdSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edIdHoaDon.getText().toString(),new Date());
 /**/                   HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoLuong.getText().toString()), edKhachHang.getText().toString(), edSDT.getText().toString());
                    if (pos>=0){
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos,hoaDonChiTiet);
                    }else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                }else {
                    Toast.makeText(getApplicationContext(),"Mã sách không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd: dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() * hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " +thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int checkIdSach(List<HoaDonChiTiet> lsHD, String idSach){
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++){
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getIdSach().equalsIgnoreCase(idSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }
    public int validation(){
        if (edIdSach.getText().toString().isEmpty()||edSoLuong.getText().toString().isEmpty()||edIdHoaDon.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}