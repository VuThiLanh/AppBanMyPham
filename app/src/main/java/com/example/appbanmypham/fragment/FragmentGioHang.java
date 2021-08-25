package com.example.appbanmypham.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbanmypham.Activity.DangNhap;
import com.example.appbanmypham.Adapter.GioHangAdapter;
import com.example.appbanmypham.Adapter.SanPhamAdapter;
import com.example.appbanmypham.MainActivity;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGioHang#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentGioHang extends Fragment implements GioHangAdapter.ItemClickListener{
    Button btnThanhToan;
    ImageView imgQuayLaiDetail;
    GioHangAdapter gioHangAdapter;
    TextView tv_thanhtien,tvThongBao;
    View view;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGioHang.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGioHang newInstance(String param1, String param2, String param3,String param4) {
        FragmentGioHang fragment = new FragmentGioHang();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }
    public static FragmentGioHang getInstance() {
        FragmentGioHang fragment = new FragmentGioHang();
        return fragment;
    }
    public FragmentGioHang() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gio_hang, container, false);
        AnhXa(view);

        RecyclerView recycleviewTatCa = view.findViewById(R.id.rcGioHang);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recycleviewTatCa.setLayoutManager(layoutManager);



        GioHangAdapter adapter = new GioHangAdapter(Detail.listgiohang,this,getActivity());
        recycleviewTatCa.setAdapter(adapter);
        int d=0;
        for(int i=0;i<Detail.listgiohang.size();i++){
            d=d+Detail.listgiohang.get(i).getSoLuongMua()*Detail.listgiohang.get(i).getGiaSP();
        }
        tv_thanhtien.setText(d+"");

        imgQuayLaiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                transaction.add(R.id.content_frame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return  view;
    }
    private  void  AnhXa(View view){
        btnThanhToan=view.findViewById(R.id.btnThanhToan);
        tv_thanhtien=view.findViewById(R.id.tvTongTien);
        imgQuayLaiDetail=view.findViewById(R.id.imageViewQuayLai);

    }

    @Override
    public void onItemClick(GioHang gioHang) {
        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
        ad.setTitle("Thông báo");
        String msg = String.format("Bạn chắc chắn xóa sản phẩm !");
        ad.setMessage(msg);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        ad.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                for(int i=0;i<Detail.listgiohang.size();i++) {
                    if (Detail.listgiohang.get(i).getTenSP().contains(gioHang.getTenSP())) {
                        Detail.listgiohang.remove(Detail.listgiohang.get(i));
                    }
                }
                RecyclerView recycleviewTatCa = view.findViewById(R.id.rcGioHang);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                recycleviewTatCa.setLayoutManager(layoutManager);



                GioHangAdapter adapter = new GioHangAdapter(Detail.listgiohang,FragmentGioHang.this,getActivity());
                recycleviewTatCa.setAdapter(adapter);
                int d=0;
                for(int i=0;i<Detail.listgiohang.size();i++){
                    d=d+Detail.listgiohang.get(i).getSoLuongMua()*Detail.listgiohang.get(i).getGiaSP();
                }
                tv_thanhtien.setText(d+"");
            }
        });
        ad.show();
    }
//    private void EvenUtil() {
//        int tongtien = 0;
//        for(int i=0;i<HomeFragment.manggiohang.size();i++){
//            tongtien+=HomeFragment.manggiohang.get(i).getGiaSP();
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        tv_thanhtien.setText(decimalFormat.format(tongtien)+"");
//    }
//
//    private void checkData() {
//        if(HomeFragment.manggiohang.size()<=0){
//            gioHangAdapter.notifyDataSetChanged();
//            tvThongBao.setVisibility(View.VISIBLE);
//            lvGioHang.setVisibility(View.INVISIBLE);
//
//        }else {
//            gioHangAdapter.notifyDataSetChanged();
//            tvThongBao.setVisibility(View.INVISIBLE);
//            lvGioHang.setVisibility(View.VISIBLE);
//        }
//
//    }


}