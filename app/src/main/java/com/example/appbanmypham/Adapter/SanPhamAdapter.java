package com.example.appbanmypham.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.SanPham;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends  RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> implements Filterable {

    List<SanPham> list;
    List<SanPham> listold;
    private  ItemClickListener clickListener;
    Context context;
    public  SanPhamAdapter(List<SanPham> list,ItemClickListener clickListener,Context context){
        this.list=list;
        this.clickListener= clickListener;
        this.context=context;
        this.listold=list;
    }
    @NonNull
    @NotNull
    @Override
    public SanPhamAdapter.SanPhamViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngang,parent,false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SanPhamAdapter.SanPhamViewHolder holder, int position) {
        holder.item_list_sp.setText(list.get(position).getTenSP());
        holder.item_list_gia.setText(list.get(position).getGiaSP()+"");
//        holder.item_list_anh.setImageResource(list.get(position).getHinhAnh());
        Glide.with(context).load(list.get(position).getHinhAnh()).into(holder.item_list_anh);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(list.get(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    class SanPhamViewHolder extends RecyclerView.ViewHolder{
        TextView item_list_sp,item_list_gia;
        ImageView item_list_anh;
        ConstraintLayout item;
        public  SanPhamViewHolder(View view){
            super(view);
            item_list_sp=view.findViewById(R.id.tv_list_tensp);
            item_list_gia=view.findViewById(R.id.tv_list_giaban);
            item_list_anh=view.findViewById(R.id.img_list_anh);
            item=view.findViewById(R.id.layout_item);
        }
    }
    public  interface ItemClickListener{
        public void onItemClick(SanPham  sanPham);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    list=listold;
                }
                else {
                    List<SanPham> listnew = new ArrayList<>();
                    for (SanPham sanPham : listold){
                        if(sanPham.getTenSP().toLowerCase().contains(strSearch.toLowerCase())){
                            listnew.add(sanPham);
                        }
                    }
                    list=listnew;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list=(List<SanPham>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}