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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanmypham.Activity.DangNhap;
import com.example.appbanmypham.Adapter.GioHangAdapter;
import com.example.appbanmypham.MainActivity;
import com.example.appbanmypham.R;
import com.example.appbanmypham.database.APIService;
import com.example.appbanmypham.model.GioHang;
import com.example.appbanmypham.model.ThanhToan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    TextView tv_thanhtoan,tv_thongbao;
    RecyclerView rcHangThanhToan;
    ImageView imgQuayLia;
    EditText edTenMguoiNhan,edSDTNguoiNhan,edDiaChiNguoiNhan;
    Button btnDatHang;
    public static List<ThanhToan> mangThanhToan = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    long d=0;

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
//

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
        edTenMguoiNhan= view.findViewById(R.id.edNguoiNhan);
        edDiaChiNguoiNhan=view.findViewById(R.id.edDiaChiNguoiNhan);
        edSDTNguoiNhan=view.findViewById(R.id.edSDTNguoiNhan);
        rcHangThanhToan=view.findViewById(R.id.rcHangThanhToan);
        tv_thanhtoan=view.findViewById(R.id.tvTongTienThanhToan);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        btnDatHang = view.findViewById(R.id.btnDatHang);

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                EditText ed= view.findViewById(R.id.ed_email_update);
                String id = user.getUid()+"";

                ThanhToan thanhToan = new ThanhToan(edTenMguoiNhan.getText().toString(),edSDTNguoiNhan.getText().toString(),edDiaChiNguoiNhan.getText().toString(),Detail.listgiohang,d);
                mangThanhToan.add(thanhToan);
                for (int j=0;j<mangThanhToan.size();j++){
                    String donhang="donhang"+j+"";
                    databaseReference.child("user").child(id).child("email").setValue(user.getEmail());
                    databaseReference.child("user").child(id).child("donhang").child(donhang).child("nguoinhan").setValue(mangThanhToan.get(j).getTen()+"");
                    databaseReference.child("user").child(id).child("donhang").child(donhang).child("sdt").setValue(mangThanhToan.get(j).getSdt()+"");
                    databaseReference.child("user").child(id).child("donhang").child(donhang).child("diachi").setValue(mangThanhToan.get(j).getDiachi()+"");
                    databaseReference.child("user").child(id).child("donhang").child(donhang).child("tongtien").setValue(mangThanhToan.get(j).getTongtiem()+"");
                    databaseReference.child("user").child(id).child("donhang").child(donhang).child("sanpham").setValue(mangThanhToan.get(j).getGioHangs());
                    AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                    ad.setTitle("Thông báo");
                    String msg = String.format("Đặt hàng thành công !");
                    ad.setMessage(msg);
                    ad.setIcon(android.R.drawable.ic_dialog_info);
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Detail.listgiohang.removeAll(Detail.listgiohang);
                            Fragment fragment = HomeFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                            transaction.add(R.id.content_frame,fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                    ad.show();
                }
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
    private  void sendPosts(View view){
        edTenMguoiNhan= view.findViewById(R.id.edNguoiNhan);
        edDiaChiNguoiNhan=view.findViewById(R.id.edDiaChiNguoiNhan);
        edSDTNguoiNhan=view.findViewById(R.id.edSDTNguoiNhan);
        rcHangThanhToan=view.findViewById(R.id.rcHangThanhToan);
        tv_thanhtoan=view.findViewById(R.id.tvTongTienThanhToan);

        long d=0;
        for(int i=0;i<Detail.listgiohang.size();i++){
            d=d+Detail.listgiohang.get(i).getSoLuongMua()*Detail.listgiohang.get(i).getGiaSP();
        }

        ThanhToan thanhToan = new ThanhToan(edTenMguoiNhan.getText().toString(),edSDTNguoiNhan.getText().toString(),edDiaChiNguoiNhan.getText().toString(),Detail.listgiohang,d);

        APIService.apiService.sendPosts(thanhToan).enqueue(new Callback<ThanhToan>() {
            @Override
            public void onResponse(Call<ThanhToan> call, Response<ThanhToan> response) {
                ThanhToan thanhToan1 = response.body();
                mangThanhToan.add(thanhToan1);
                AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                ad.setTitle("Thông báo");
                String msg = String.format("Đặt hàng thành công !");
                ad.setMessage(msg);
                ad.setIcon(android.R.drawable.ic_dialog_info);
                ad.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Detail.listgiohang.removeAll(Detail.listgiohang);
                        Fragment fragment = HomeFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                        transaction.add(R.id.content_frame,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
                ad.show();
            }

            @Override
            public void onFailure(Call<ThanhToan> call, Throwable t) {
                Toast.makeText(getActivity(),"Đặt hàng thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

}