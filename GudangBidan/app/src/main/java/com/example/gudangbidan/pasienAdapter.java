package com.example.gudangbidan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class pasienAdapter extends RecyclerView.Adapter<pasienAdapter.PasienViewHolder> {

    private List<pasien> pasienList = new ArrayList<>();

    public pasienAdapter(List<pasien> pasienList){
        this.pasienList = pasienList;
    }

    @NonNull
    @Override
    public pasienAdapter.PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdata, parent, false);
        PasienViewHolder pasienViewHolder = new PasienViewHolder(view);
        return pasienViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pasienAdapter.PasienViewHolder holder, int position) {
        holder.textNama.setText(pasienList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public static class PasienViewHolder extends RecyclerView.ViewHolder{
        TextView textNama;
        public PasienViewHolder(View itemView) {
            super(itemView);

            textNama = (TextView) itemView.findViewById(R.id.textNama);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
