package com.example.isomanhealthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AkunAdapter extends RecyclerView.Adapter<AkunAdapter.AkunHolder> {

    List<AkunClass> eList;
    Context context;

    public AkunAdapter(List<AkunClass> eList, Context context) {
        this.eList = eList;
        this.context = context;
    }

    @NonNull
    @Override
    public AkunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rowakun, parent, false);

        return new AkunHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AkunHolder holder, int position) {
        holder.judul.setText(eList.get(position).getJudul());
        holder.isi.setText(eList.get(position).getIsi());
    }

    @Override
    public int getItemCount() {
        return eList.size();
    }

    public class AkunHolder extends RecyclerView.ViewHolder {
        TextView judul, isi;

        public AkunHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            isi = itemView.findViewById(R.id.isi);
        }
    }
}
