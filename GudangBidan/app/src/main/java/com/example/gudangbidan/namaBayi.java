package com.example.gudangbidan;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class namaBayi extends Fragment {

    private List<bayi> bayiList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private bayiAdapter adapter;
    private DatabaseHelper dbHandler;
    private TextView txt_resultadapter;

    public namaBayi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Data");
        View view = inflater.inflate(R.layout.fragment_nama_bayi, container, false);

        //INIT Recyclerview beserta adapternya
        recyclerView = (RecyclerView) view.findViewById(R.id.listP);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DatabaseHelper(getActivity());
        bayiList = dbHandler.getBayi();
        adapter = new bayiAdapter(bayiList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        txt_resultadapter = (TextView) view.findViewById(R.id.txt_resultadapter);

        //mengecek apakah ada data di dalam recyclerview
        if(adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // TODO Handle item click

                            bayi b = bayiList.get(position);

                            //menyimpan id bayi sementara ke dalam session
                            SharedPreferences mSettings = getActivity().getSharedPreferences("simpan1", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt("idBayi",b.getIdBayi());
                            editor.apply();

                            /*
                            String nama = p.getNama();
                            Toast.makeText(getActivity(), "Klik di " + nama, Toast.LENGTH_SHORT).show();
                            */

                            Intent i = new Intent(getActivity(),dataBayi.class);

                            //menghubungkan activity splahscreen ke MainActivity
                            startActivity(i);
                        }
                    })
            );
        }

        return view;
    }



}
