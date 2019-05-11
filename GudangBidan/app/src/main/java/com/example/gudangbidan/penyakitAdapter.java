package com.example.gudangbidan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class penyakitAdapter extends RecyclerView.Adapter<penyakitAdapter.PenyakitViewHolder> {

    private List<penyakit> penyakitList = new ArrayList<>();

    public penyakitAdapter (List<penyakit> penyakitList){
        this.penyakitList = penyakitList;
    }

    @NonNull
    @Override
    public penyakitAdapter.PenyakitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrekampasien,parent,false);
        PenyakitViewHolder penyakitViewHolder = new PenyakitViewHolder(view);

        return penyakitViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull penyakitAdapter.PenyakitViewHolder holder, int position) {
        holder.textTanggal.setText(penyakitList.get(position).getTanggal_periksa());
        holder.textIsiKeluhan.setText(penyakitList.get(position).getKeluhan());
        holder.textIsiDiagnosa.setText(penyakitList.get(position).getDiagnosa());
    }

    @Override
    public int getItemCount() {
        return penyakitList.size();
    }


    public static class PenyakitViewHolder extends RecyclerView.ViewHolder {
        TextView textTanggal;
        TextView textIsiKeluhan;
        TextView textIsiDiagnosa;

        public PenyakitViewHolder(View itemView) {
            super(itemView);

            textTanggal = itemView.findViewById(R.id.textTanggal);
            textIsiKeluhan = itemView.findViewById(R.id.textIsiKeluhan);
            textIsiDiagnosa = itemView.findViewById(R.id.textIsiDiagnosa);
        }
    }
}
