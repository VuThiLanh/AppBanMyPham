package com.example.appbanmypham.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanmypham.Adapter.SanPhamAdapter;
import com.example.appbanmypham.Adapter.SanPhamBanChayAdapter;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.GioHang;
import com.example.appbanmypham.model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SanPhamAdapter.ItemClickListener {
    private ArrayList<SanPham> list= new ArrayList<>();
    private ArrayList<SanPham> listbanchay= new ArrayList<>();
    SanPhamAdapter adapter ;
    public static ArrayList<GioHang> manggiohang;
    TextView search;
    String str ="http://demo1777035.mockable.io/sanpham";
    String str1 ="http://demo1777035.mockable.io/sanphambanchay";
    View view;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    public  static  HomeFragment getInstance(){
        HomeFragment fragment = new HomeFragment();
        return  fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        new ReadJSonObject().execute();
        new ReadJSonObjectBanChay().execute();
        search=view.findViewById(R.id.editTextSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = DanhSachTimKiem.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
                transaction.add(R.id.content_frame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        if(manggiohang!=null){

        }else {
            manggiohang=new ArrayList<>();
        }
        return  view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
        }
    }
    @Override
    public void onItemClick(SanPham sanPham) {
        Fragment fragment = Detail.newInstance(sanPham.getTenSP(),sanPham.getGiaSP()+"",sanPham.getHinhAnh()+"",sanPham.getMoTa());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame));
        transaction.add(R.id.content_frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initRecyclerView(View view){
        RecyclerView recycleviewTatCa = view.findViewById(R.id.recycleview_tatcasp);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recycleviewTatCa.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(list,this,getActivity());
        recycleviewTatCa.setAdapter(adapter);
    }
    private void initRecyclerView1(View view){
        RecyclerView recycleviewSpBanChay = view.findViewById(R.id.recycleview_spbanchay);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recycleviewSpBanChay.setLayoutManager(layoutManager);
        SanPhamBanChayAdapter adapter = new SanPhamBanChayAdapter(listbanchay,this,getActivity());
        recycleviewSpBanChay.setAdapter(adapter);
    }

    private class ReadJSonObject extends AsyncTask<Void, Void,String>{
        String result ="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(str);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteChar;
                while ((byteChar=is.read())!=-1){
                    result+=(char) byteChar;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            try{
                SanPham sanPham;
                ArrayList<SanPham> sanPhams = new ArrayList<>();
                JSONArray array = new JSONArray(result);
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    String idsp = jsonObject.getString("idsp");
                    String tensp = jsonObject.getString("tensp");
                    int giasp = jsonObject.getInt("giasp");
                    String hinhanh=jsonObject.getString("hinhanh");
                    String mota=jsonObject.getString("mota");
                    String idloai=jsonObject.getString("idloai");
                    sanPham  = new SanPham(idsp,tensp,giasp,hinhanh,mota,idloai);
                    sanPhams.add(sanPham);
                }
                list=sanPhams;
                initRecyclerView(view);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private class ReadJSonObjectBanChay extends AsyncTask<Void, Void,String>{
        String result ="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(str1);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteChar;
                while ((byteChar=is.read())!=-1){
                    result+=(char) byteChar;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            try{
                SanPham sanPham;
                ArrayList<SanPham> sanPhams = new ArrayList<>();
                JSONArray array = new JSONArray(result);
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    String idsp = jsonObject.getString("idspbanchay");
                    String tensp = jsonObject.getString("tenspbanchay");
                    int giasp = jsonObject.getInt("giaspbanchay");
                    String hinhanh=jsonObject.getString("hinhanhbanchay");
                    String mota=jsonObject.getString("motabanchay");
                    String idloai=jsonObject.getString("idloaibanchay");
                    sanPham  = new SanPham(idsp,tensp,giasp,hinhanh,mota,idloai);
                    sanPhams.add(sanPham);
                }
                listbanchay=sanPhams;
                initRecyclerView1(view);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
