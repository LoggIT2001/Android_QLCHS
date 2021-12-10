package com.example.tvs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tvs.dao.HoaDonChiTietDAO;

public class ThongKeActivity extends AppCompatActivity{
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setTitle("Doanh Thu");
        setContentView(R.layout.activity_thong_ke);
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO  = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng nay: " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}
