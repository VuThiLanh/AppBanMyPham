package com.example.appbanmypham.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.Activity.DangKy;
import com.example.appbanmypham.Adapter.GioHangAdapter;
import com.example.appbanmypham.Adapter.SanPhamAdapter;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;
import com.example.appbanmypham.model.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Detail#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Detail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    public static List<GioHang> listgiohang=new ArrayList<>();

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;


    Button btnThemVaoGioHang;
    EditText edThemSL;
    ImageView img_giohang;


    public Detail() {
        // Required empty public constructor
    }
    public static Detail newInstance(String param1, String param2,String param3,String param4) {
        Detail fragment = new Detail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    public static Detail getInstance() {
        Detail fragment = new Detail();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mParam1=getArguments().getString(ARG_PARAM1);
            mParam2=getArguments().getString(ARG_PARAM2);
            mParam3=getArguments().getString(ARG_PARAM3);
            mParam4=getArguments().getString(ARG_PARAM4);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        TextView tv_tensp_detail = view.findViewById(R.id.tv_tensp_detail);
        TextView tv_gia_detail = view.findViewById(R.id.tv_giaban_detail);
        ImageView tv_anhsp_detail = view.findViewById(R.id.imgAnhDetail);
        TextView tv_mota_detail = view.findViewById(R.id.tv_mota_detail);
        ImageView tv_quaylai = view.findViewById(R.id.imageViewQuayLai);
        EditText slmua =view.findViewById(R.id.tvSoLuongMua);

        tv_tensp_detail.setText(mParam1);
        tv_gia_detail.setText(mParam2);
        tv_mota_detail.setText(mParam4);
        Glide.with(getActivity()).load(mParam3).into(tv_anhsp_detail);


        tv_quaylai.setOnClickListener(new View.OnClickListener() {
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
        btnThemVaoGioHang = view.findViewById(R.id.btnThemVaoGioHang);
        edThemSL=view.findViewById(R.id.tvSoLuongMua);

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soLuong=slmua.getText().toString().trim();
                if(soLuong.isEmpty()){
                    AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                    ad.setTitle("Thông báo");
                    String msg = String.format("Vui lòng nhập số lượng mua!");
                    ad.setMessage(msg);
                    ad.setIcon(android.R.drawable.ic_dialog_info);
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    ad.show();
                }
                else if(Integer.parseInt(soLuong)<=0){
                    AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                    ad.setTitle("Thông báo");
                    String msg = String.format("Số lượng mua lớn hơn 0!");
                    ad.setMessage(msg);
                    ad.setIcon(android.R.drawable.ic_dialog_info);
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    ad.show();
                }
                else{
                    GioHang gioHang1 = new GioHang(mParam1,Integer.parseInt(mParam2),mParam3,Integer.parseInt(soLuong));
                    listgiohang.add(gioHang1);
                    GioHang gioHang = new GioHang(mParam1,Integer.parseInt(mParam2),mParam3,Integer.parseInt(soLuong));
                    listgiohang.add(gioHang);

                    for(int i=0;i<listgiohang.size();i++){
                            if(listgiohang.get(i).getTenSP().contains(mParam1)){
                                listgiohang.remove(listgiohang.get(i));
                            }
                    }
                    Fragment fragment = FragmentGioHang.getInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                    transaction.add(R.id.content_frame,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
//
            }
                
        });
        img_giohang=view.findViewById(R.id.imageView4);
        img_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = FragmentGioHang.getInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                transaction.add(R.id.content_frame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}