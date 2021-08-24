package com.example.appbanmypham.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.SanPham;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class SanPhamBanChayAdapter extends  RecyclerView.Adapter<SanPhamBanChayAdapter.SanPhamBanChayViewHolder> {
    List<SanPham> list;
    private SanPhamAdapter.ItemClickListener clickListener;
    Context context;
    public  SanPhamBanChayAdapter(List<SanPham> list, SanPhamAdapter.ItemClickListener clickListener, Context context){
        this.list=list;
        this.clickListener= clickListener;
        this.context=context;
    }
    @NonNull
    @NotNull
    @Override
    public SanPhamBanChayAdapter.SanPhamBanChayViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphambanchay,parent,false);
        return new SanPhamBanChayAdapter.SanPhamBanChayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SanPhamBanChayAdapter.SanPhamBanChayViewHolder holder, int position) {
        holder.tenspbanchay.setText(list.get(position).getTenSP());
        Glide.with(context).load(list.get(position).getHinhAnh()).into(holder.anhspbanchay);
        holder.item_spbanchay.setOnClickListener(new View.OnClickListener() {
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

    class SanPhamBanChayViewHolder extends RecyclerView.ViewHolder{
        TextView tenspbanchay;
        ImageView anhspbanchay;
        CardView item_spbanchay;
        public  SanPhamBanChayViewHolder(View view){
            super(view);
            tenspbanchay=view.findViewById(R.id.tv_tenspbanchay);
            anhspbanchay=view.findViewById(R.id.tv_spbanchay);
            item_spbanchay=view.findViewById(R.id.item_spbanchay);
        }
    }
    public  interface ItemClickListener{
        public void onItemClick(SanPham  sanPham);
    }
}
