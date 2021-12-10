package com.example.tvs;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tvs.adapter.NguoiDungAdapter;
import com.example.tvs.dao.NguoiDungDAO;
import com.example.tvs.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("ADMIN");
        setContentView(R.layout.activity_list_nguoi_dung);
        lvNguoiDung = (ListView) findViewById(R.id.lvNguoiDung);

        nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();

        adapter = new NguoiDungAdapter(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListNguoiDungActivity.this, NguoiDungDetailActivity.class);
            Bundle b = new Bundle();
            b.putString("USERNAME", dsNguoiDung.get(position).getUsername());
            b.putString("FULLNAME", dsNguoiDung.get(position).getName());
            b.putString("PHONE", dsNguoiDung.get(position).getPhone());
            b.putString("MAIL", dsNguoiDung.get(position).getMail());
            b.putString("ADDRESS", dsNguoiDung.get(position).getAddress());
            intent.putExtras(b);
            startActivity(intent);
        });
        lvNguoiDung.setOnItemLongClickListener((parent, view, postion, id) ->{
            return false;
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.add:
                intent = new Intent(ListNguoiDungActivity.this, NguoiDungActivity.class);
                startActivity(intent);
                return true;
            case R.id.changePass:
                intent = new Intent(ListNguoiDungActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                return true;
            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
//                edit.clear();
                edit.commit();
                intent = new Intent(ListNguoiDungActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}