package com.example.appbanmypham.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;
import com.example.appbanmypham.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHoder> {

    List<GioHang> gioHangList;
    Context context;
    private GioHangAdapter.ItemClickListener clickListener;
    public GioHangAdapter(List<GioHang> gioHangList, GioHangAdapter.ItemClickListener clickListener, Context context) {
        this.gioHangList=gioHangList;
        this.context=context;
        this.clickListener= clickListener;
    }
    public  interface ItemClickListener{
        public void onItemClick(GioHang gioHang);
    }
    @NonNull
    @Override
    public GioHangAdapter.GioHangViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_gio_hang,parent,false);
        return new GioHangAdapter.GioHangViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.GioHangViewHoder holder, int position) {
        holder.tv_tenspgiohang.setText(gioHangList.get(position).getTenSP());
        holder.tv_giatiengiohang.setText(gioHangList.get(position).getGiaSP()+"");
        holder.tv_soluonggiohang.setText(gioHangList.get(position).getSoLuongMua()+"");
        Glide.with(context).load(gioHangList.get(position).getHinhAnh()).into(holder.img_anhgiohang);
        holder.item_don_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(gioHangList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class GioHangViewHoder extends RecyclerView.ViewHolder {
        TextView tv_tenspgiohang,tv_soluonggiohang,tv_conggiohang,tv_trugiohang,tv_giatiengiohang;
        ImageView img_anhgiohang;
        LinearLayout item_don_hang;

        public GioHangViewHoder( View itemView) {
            super(itemView);
            tv_tenspgiohang=itemView.findViewById(R.id.tv_tengiohang);
            tv_giatiengiohang=itemView.findViewById(R.id.tv_giatiengiohang);
            tv_soluonggiohang=itemView.findViewById(R.id.tvSoLuongGioHang);
            img_anhgiohang=itemView.findViewById(R.id.imgangiohang);
            item_don_hang=itemView.findViewById(R.id.item_don_hang);
        }
    }
}
