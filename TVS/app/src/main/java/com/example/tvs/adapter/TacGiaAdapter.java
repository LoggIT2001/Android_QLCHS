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
import com.example.tvs.dao.TacGiaDAO;
import com.example.tvs.model.TacGia;

import java.util.List;

public class TacGiaAdapter extends BaseAdapter{
    List<TacGia> arrTacGia;
    public Activity context;
    public LayoutInflater inflater;
    TacGiaDAO tacGiaDAO;

    public TacGiaAdapter(Activity context, List<TacGia> arrayTacGia) {
        super();
        this.context = context;
        this.arrTacGia = arrayTacGia;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tacGiaDAO = new TacGiaDAO(context);
    }
    @Override
    public int getCount() {
        return arrTacGia.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTacGia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {

        ImageView img;
        TextView txtIdTacGia;
        TextView txtTenTacGia;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_tacgia, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtIdTacGia = (TextView) convertView.findViewById(R.id.tvIdTacGia);
            holder.txtTenTacGia = (TextView) convertView.findViewById(R.id.tvTenTacGia);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tacGiaDAO.deleteTacGiaByID(arrTacGia.get(position).getIdTacGia());
                    arrTacGia.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        TacGia _entry = (TacGia) arrTacGia.get(position);
        holder.img.setImageResource(R.drawable.emone);
        holder.txtIdTacGia.setText(_entry.getIdTacGia());
        holder.txtTenTacGia.setText(_entry.getTenTacGia());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<TacGia> items){
        this.arrTacGia = items;
        notifyDataSetChanged();

    }
}
