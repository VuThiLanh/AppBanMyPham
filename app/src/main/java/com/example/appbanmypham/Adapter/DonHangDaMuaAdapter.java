package com.example.appbanmypham.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;
import com.example.appbanmypham.model.ThanhToan;

import java.util.List;

public class DonHangDaMuaAdapter extends RecyclerView.Adapter<DonHangDaMuaAdapter.DonHangDaMuaViewHoder> {
    List<ThanhToan> thanhToanList;
    Context context;
    public DonHangDaMuaAdapter( List<ThanhToan> thanhToanList, Context context) {
        this.thanhToanList=thanhToanList;
        this.context=context;
    }

    @NonNull
    @Override
    public DonHangDaMuaAdapter.DonHangDaMuaViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhangdadat,parent,false);
        return new DonHangDaMuaAdapter.DonHangDaMuaViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangDaMuaAdapter.DonHangDaMuaViewHoder holder, int position) {
        holder.tv_nguoinhan.setText(thanhToanList.get(position).getTen());
        holder.tv_sdtnguoinhan.setText(thanhToanList.get(position).getSdt());
        holder.tv_diachinguoinhan.setText(thanhToanList.get(position).getDiachi());
        holder.tv_sanpham.setText(thanhToanList.get(position).toString().replace("[","").replace("]",""));
        holder.tv_tongtien.setText(thanhToanList.get(position).getTongtiem()+"");

    }

    @Override
    public int getItemCount() {
        return thanhToanList.size();
    }

    public class DonHangDaMuaViewHoder extends RecyclerView.ViewHolder {
        TextView tv_nguoinhan,tv_sdtnguoinhan,tv_diachinguoinhan,tv_sanpham,tv_tongtien;

        public DonHangDaMuaViewHoder( View itemView) {
            super(itemView);
            tv_nguoinhan=itemView.findViewById(R.id.tv_nguoinhan);
            tv_sdtnguoinhan=itemView.findViewById(R.id.tv_sdtnguoinhan);
            tv_diachinguoinhan=itemView.findViewById(R.id.tv_diachinguoinhan);
            tv_sanpham=itemView.findViewById(R.id.tv_sanphammua);
            tv_tongtien=itemView.findViewById(R.id.tv_tongtiennguoinhan);
        }
    }
}
