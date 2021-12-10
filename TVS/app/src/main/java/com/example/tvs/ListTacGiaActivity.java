package com.example.tvs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tvs.adapter.TacGiaAdapter;
import com.example.tvs.dao.TacGiaDAO;
import com.example.tvs.model.TacGia;

import java.util.ArrayList;
import java.util.List;

public class ListTacGiaActivity extends AppCompatActivity {
    public static List<TacGia> dsTacgia = new ArrayList<>();
    ListView lvTacGia;
    TacGiaAdapter adapter = null;
    TacGiaDAO tacGiaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TÁC GIẢ");
        setContentView(R.layout.activity_list_tac_gia);
        lvTacGia = (ListView) findViewById(R.id.lvTacGia);
        registerForContextMenu(lvTacGia);
        tacGiaDAO = new TacGiaDAO(ListTacGiaActivity.this);
        dsTacgia = tacGiaDAO.getAllTacGia();

        adapter = new TacGiaAdapter(this, dsTacgia);
        lvTacGia.setAdapter(adapter);

        lvTacGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTacGiaActivity.this,TacGiaActivity.class);
                Bundle b = new Bundle();
                b.putString("MATHELOAI", dsTacgia.get(position).getIdTacGia());
                b.putString("TENTHELOAI", dsTacgia.get(position).getTenTacGia());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tacgia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListTacGiaActivity.this,TacGiaActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsTacgia.clear();
        dsTacgia = tacGiaDAO.getAllTacGia();
        adapter.changeDataset(dsTacgia);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn thông tin");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(ListTacGiaActivity.this,TacGiaActivity.class);
                startActivity(intent1);
                return(true);
            case R.id.menu_ctx_del:
                Intent intent2 = new Intent(ListTacGiaActivity.this,TacGiaActivity.class);
                startActivity(intent2);
                return(true);
        }
        return super.onContextItemSelected(item);

    }
}
