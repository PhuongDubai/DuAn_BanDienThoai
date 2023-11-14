package com.example.duan_bandienthoai.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.mode.DienThoai;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.viewholder>{
    private final Context context;
    private final ArrayList<DienThoai> list;
    DienThoaiDao dtDao;

    public DienThoaiAdapter(Context context, ArrayList<DienThoai> list) {
        this.context = context;
        this.list = list;
        dtDao = new DienThoaiDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sp_moi,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.txttendt.setText(list.get(position).getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiadt.setText("Giá: "+decimalFormat.format(list.get(position).getPrice()) +" VND");

        int img_id =((Activity)context).getResources().getIdentifier(list.get(position).getAnh(),"drawable",((Activity)context).getPackageName());
        holder.anh.setImageResource(img_id);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView txttendt,txtGiadt;
        ImageView anh;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txttendt = itemView.findViewById(R.id.itemsp_ten);
            txtGiadt = itemView.findViewById(R.id.itemsp_gia);
            anh = itemView.findViewById(R.id.itemsp_image);
        }
    }
}
