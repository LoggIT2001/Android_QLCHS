package com.example.tvs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvs.dao.HoaDonDAO;
import com.example.tvs.model.HoaDon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText edNgayMua, edIdHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM HOÁ ĐƠN");
        setContentView(R.layout.activity_hoa_don);
        edNgayMua = (EditText) findViewById(R.id.edNgayMua);
        edIdHoaDon = (EditText) findViewById(R.id.edIdHoaDon);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
    private void setDate(final Calendar calendar) {

        edNgayMua.setText(sdf.format(calendar.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }
    }
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(),"date");
    }
    public void ADDHoaDon(View view) {
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);

        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new HoaDon(edIdHoaDon.getText().toString(),sdf.parse(edNgayMua.getText().toString()));
                if (hoaDonDAO.inserHoaDon(hoaDon) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HoaDonActivity.this,HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edIdHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int validation(){
        if (edIdHoaDon.getText().toString().isEmpty()||edNgayMua.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}