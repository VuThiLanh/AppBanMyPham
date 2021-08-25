package com.example.appbanmypham.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbanmypham.Adapter.SanPhamAdapter;
import com.example.appbanmypham.R;
import com.example.appbanmypham.model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTatCaSp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTatCaSp extends Fragment implements SanPhamAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<SanPham> list= new ArrayList<>();
    String str ="http://demo1777035.mockable.io/sanpham";
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTatCaSp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTatCaSp.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTatCaSp newInstance(String param1, String param2) {
        FragmentTatCaSp fragment = new FragmentTatCaSp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        view= inflater.inflate(R.layout.fragment_tat_ca_sp, container, false);
        new ReadJSonObject().execute();
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

    private void initRecyclerView(View view){
        RecyclerView recycleviewTatCa = view.findViewById(R.id.rcTatCaSP);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recycleviewTatCa.setLayoutManager(layoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(list,this,getActivity());
        recycleviewTatCa.setAdapter(adapter);
    }
    private class ReadJSonObject extends AsyncTask<Void, Void,String> {
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
}