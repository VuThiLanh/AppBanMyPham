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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanmypham.Adapter.GioHangAdapter;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThanhToanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThanhToanFragment extends Fragment implements GioHangAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    TextView tv_thanhtoan;
    RecyclerView rcHangThanhToan;
    ImageView imgQuayLia;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThanhToanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThanhToanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThanhToanFragment newInstance(String param1, String param2) {
        ThanhToanFragment fragment = new ThanhToanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static ThanhToanFragment getInstance() {
        ThanhToanFragment fragment = new ThanhToanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        AnhXa(view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rcHangThanhToan.setLayoutManager(layoutManager);
        GioHangAdapter adapter = new GioHangAdapter(Detail.listgiohang, this,getActivity());
        rcHangThanhToan.setAdapter(adapter);

        int d=0;
        for(int i=0;i<Detail.listgiohang.size();i++){
            d=d+Detail.listgiohang.get(i).getSoLuongMua()*Detail.listgiohang.get(i).getGiaSP();
        }
        tv_thanhtoan.setText(d+"");

        imgQuayLia.setOnClickListener(new View.OnClickListener() {
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

    private  void AnhXa(View view){
        tv_thanhtoan=view.findViewById(R.id.tvTongTienThanhToan);
        rcHangThanhToan=view.findViewById(R.id.rcHangThanhToan);
        imgQuayLia=view.findViewById(R.id.imageViewQuayLaiGiooHang);
    }
    @Override
    public void onItemClick(GioHang gioHang) {
    }

}