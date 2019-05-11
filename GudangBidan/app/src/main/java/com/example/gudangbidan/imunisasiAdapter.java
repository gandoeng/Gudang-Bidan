package com.example.gudangbidan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class imunisasiAdapter extends RecyclerView.Adapter<imunisasiAdapter.ImunisasiViewHolder> {

    private List<imunisasi> imunisasiList = new ArrayList<>();

    public imunisasiAdapter (List<imunisasi> imunisasiList){
        this.imunisasiList = imunisasiList;
    }

    @NonNull
    @Override
    public imunisasiAdapter.ImunisasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrekamimunisasi,parent,false);
        ImunisasiViewHolder imunisasiViewHolder = new ImunisasiViewHolder(view);

        return imunisasiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull imunisasiAdapter.ImunisasiViewHolder holder, int position) {
        holder.textTanggal.setText(imunisasiList.get(position).getTgl_imunisasi());
        holder.textIsiImunisasi.setText(imunisasiList.get(position).getJenis());
    }

    @Override
    public int getItemCount() {
        return imunisasiList.size();
    }


    public static class ImunisasiViewHolder extends RecyclerView.ViewHolder {
        TextView textTanggal;
        TextView textIsiImunisasi;

        public ImunisasiViewHolder(View itemView) {
            super(itemView);

            textTanggal = itemView.findViewById(R.id.textTanggal);
            textIsiImunisasi = itemView.findViewById(R.id.textIsiImunisasi);
        }
    }
}

