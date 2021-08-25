package com.example.appbanmypham.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanmypham.Adapter.SanPhamAdapter;
import com.example.appbanmypham.Adapter.SanPhamBanChayAdapter;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class YeuThichFragment extends Fragment implements SanPhamAdapter.ItemClickListener {
    View view;
    private ArrayList<SanPham> listbanchay= new ArrayList<>();
    String str1 ="http://demo1777035.mockable.io/sanphambanchay";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=  inflater.inflate(R.layout.fragment_yeuthich, container, false);
        new ReadJSonObjectBanChay().execute();
       return view;
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
    private void initRecyclerView1(View view){
        RecyclerView recycleviewSpBanChay = view.findViewById(R.id.rcSPBanChay);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recycleviewSpBanChay.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(listbanchay,this,getActivity());
        recycleviewSpBanChay.setAdapter(adapter);
    }
    private class ReadJSonObjectBanChay extends AsyncTask<Void, Void,String> {
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
