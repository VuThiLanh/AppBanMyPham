package com.example.appbanmypham.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbanmypham.Adapter.DonHangDaMuaAdapter;
import com.example.appbanmypham.Adapter.GioHangAdapter;
import com.example.appbanmypham.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonHangDaDatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonHangDaDatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonHangDaDatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonHangDaDatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonHangDaDatFragment newInstance(String param1, String param2) {
        DonHangDaDatFragment fragment = new DonHangDaDatFragment();
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
        view = inflater.inflate(R.layout.fragment_don_hang_da_dat, container, false);

       RecyclerView rcdonhang;
       rcdonhang=view.findViewById(R.id.rcDonHangDaDat);

       LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
       rcdonhang.setLayoutManager(layoutManager);

       DonHangDaMuaAdapter adapter = new DonHangDaMuaAdapter(ThanhToanFragment.mangThanhToan,getActivity());
       rcdonhang.setAdapter(adapter);

       return  view;
    }
}