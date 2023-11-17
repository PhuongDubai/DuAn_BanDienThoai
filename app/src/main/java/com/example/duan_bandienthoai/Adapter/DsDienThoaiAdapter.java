package com.example.duan_bandienthoai.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_bandienthoai.Activity.ChiTietActivity;
import com.example.duan_bandienthoai.Interface.ItemClickListener;
import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.DienThoaiDao;
import com.example.duan_bandienthoai.mode.DienThoai;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DsDienThoaiAdapter extends RecyclerView.Adapter<DsDienThoaiAdapter.viewholder>{
    private final Context context;
    private final ArrayList<DienThoai> list;

    DienThoaiDao dtDao;

    public DsDienThoaiAdapter(Context context, ArrayList<DienThoai> list) {
        this.context = context;
        this.list = list;
        dtDao = new DienThoaiDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dien_thoai,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.txtTendt.setText(list.get(position).getName());
        holder.txtMota.setText(list.get(position).getMota());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiadt.setText("Giá: "+decimalFormat.format(list.get(position).getPrice()) +" VND");

        int img_id =((Activity)context).getResources().getIdentifier(list.get(position).getAnh(),"drawable",((Activity)context).getPackageName());
        holder.anh.setImageResource(img_id);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    //click
                    Intent intent = new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitiet",list.get(pos));
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTendt,txtGiadt,txtMota;
        ImageView anh;
        private ItemClickListener itemClickListener;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTendt = itemView.findViewById(R.id.itemdt_ten);
            txtGiadt = itemView.findViewById(R.id.itemdt_gia);
            txtMota = itemView.findViewById(R.id.itemdt_mota);


            anh = itemView.findViewById(R.id.itemdt_image);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }
    }
}
