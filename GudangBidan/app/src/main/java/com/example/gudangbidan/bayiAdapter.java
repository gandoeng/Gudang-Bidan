package com.example.gudangbidan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class bayiAdapter extends RecyclerView.Adapter<bayiAdapter.BayiViewHolder> {

    private List<bayi> bayiList = new ArrayList<>();

    public bayiAdapter(List<bayi> bayiList){
        this.bayiList = bayiList;
    }

    @NonNull
    @Override
    public bayiAdapter.BayiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbayi, parent, false);
        BayiViewHolder bayiViewHolder = new BayiViewHolder(view);
        return bayiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BayiViewHolder holder, int position) {
        holder.textRegister.setText(Integer.toString(bayiList.get(position).getIdBayi()));
        holder.textNama.setText(bayiList.get(position).getNamaBayi());
    }


    @Override
    public int getItemCount() {
        return bayiList.size();
    }

    public static class BayiViewHolder extends RecyclerView.ViewHolder{
        TextView textNama;
        TextView textRegister;
        public BayiViewHolder(View itemView) {
            super(itemView);

            textNama = (TextView) itemView.findViewById(R.id.textNama);
            textRegister = (TextView) itemView.findViewById(R.id.textRegrister);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
