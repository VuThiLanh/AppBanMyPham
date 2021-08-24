package com.example.appbanmypham.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHoder> {

    List<GioHang> gioHangList;
    Context context;

    public GioHangAdapter(List<GioHang> gioHangList,Context context) {
        this.gioHangList=gioHangList;
        this.context=context;
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
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class GioHangViewHoder extends RecyclerView.ViewHolder {
        TextView tv_tenspgiohang,tv_soluonggiohang,tv_conggiohang,tv_trugiohang,tv_giatiengiohang;
        ImageView img_anhgiohang;

        public GioHangViewHoder( View itemView) {
            super(itemView);
            tv_tenspgiohang=itemView.findViewById(R.id.tv_tengiohang);
            tv_giatiengiohang=itemView.findViewById(R.id.tv_giatiengiohang);
            tv_soluonggiohang=itemView.findViewById(R.id.tvSoLuongGioHang);
            tv_conggiohang=itemView.findViewById(R.id.tvCongGioHang);
            tv_trugiohang=itemView.findViewById(R.id.tvTruGioHang);
            img_anhgiohang=itemView.findViewById(R.id.imgangiohang);
        }
    }
//public class GioHangAdapter extends BaseAdapter {
//    Context context;
//    ArrayList<GioHang> arraygihang;
//
//    public GioHangAdapter(Context context, ArrayList<GioHang> arraygihang) {
//        this.context = context;
//        this.arraygihang = arraygihang;
//    }
//
//    @Override
//    public int getCount() {
//        return arraygihang.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return arraygihang.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//    public  class ViewHolder{
//        public TextView tv_tenspgiohang,tv_soluonggiohang,tv_conggiohang,tv_trugiohang,tv_giatiengiohang;
//        ImageView img_anhgiohang;
//    }
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder=null;
//        if(view==null){
//            viewHolder=new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.dong_gio_hang,null);
//            viewHolder.tv_tenspgiohang=view.findViewById(R.id.tv_tengiohang);
//            viewHolder.tv_giatiengiohang=view.findViewById(R.id.tv_giatiengiohang);
//            viewHolder.tv_soluonggiohang=view.findViewById(R.id.tvSoLuongGioHang);
//            viewHolder.tv_conggiohang=view.findViewById(R.id.tvCongGioHang);
//            viewHolder.tv_trugiohang=view.findViewById(R.id.tvTruGioHang);
//            viewHolder.img_anhgiohang=view.findViewById(R.id.imgangiohang);
//            view.setTag(viewHolder);
//
//        }
//        else {
//            viewHolder= (ViewHolder) view.getTag();
//        }
//        GioHang gioHang= (GioHang) getItem(i);
//        viewHolder.tv_tenspgiohang.setText(gioHang.getTenSP());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        viewHolder.tv_giatiengiohang.setText(decimalFormat.format(gioHang.getGiaSP()+""));
//        Glide.with(context).load(gioHang.getHinhAnh()).into(viewHolder.img_anhgiohang);
//        viewHolder.tv_soluonggiohang.setText(gioHang.getSoLuongMua()+"");
//        return view;
//    }
}
