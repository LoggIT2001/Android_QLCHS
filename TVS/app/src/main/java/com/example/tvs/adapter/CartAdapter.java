package com.example.tvs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvs.R;
import com.example.tvs.dao.HoaDonChiTietDAO;
import com.example.tvs.model.HoaDonChiTiet;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public CartAdapter(Activity context, List<HoaDonChiTiet> arrayHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrayHoaDonChiTiet;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }
    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {

        TextView txtIdSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtKhachHang;
        TextView txtSdt;
        TextView txtThanhTien;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder.txtIdSach = (TextView) convertView.findViewById(R.id.tvIdSach);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.txtGiaBia = (TextView) convertView.findViewById(R.id.tvGiaBia);
            holder.txtKhachHang = (TextView) convertView.findViewById(R.id.tvKhachHang);
            holder.txtSdt = (TextView) convertView.findViewById(R.id.tvSdt);
            holder.txtThanhTien = (TextView) convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(position).getIdHDCT()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        HoaDonChiTiet _entry = (HoaDonChiTiet) arrHoaDonChiTiet.get(position);
        holder.txtIdSach.setText("Mã sách: "+_entry.getSach().getIdSach());
        holder.txtSoLuong.setText("Số lượng: "+_entry.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa: "+_entry.getSach().getGiaBia() +" vnd");
        holder.txtKhachHang.setText("Khách hàng: "+_entry.getKhachHang());
        holder.txtSdt.setText("Số điện thoại: "+_entry.getSdt());
        holder.txtThanhTien.setText("Thành tiền: "+_entry.getSoLuongMua()*_entry.getSach().getGiaBia()+" vnd");

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonChiTiet> items){
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
