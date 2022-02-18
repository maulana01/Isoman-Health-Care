package com.example.isomanhealthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EditAkunAdapter extends RecyclerView.Adapter<EditAkunAdapter.EditAkunHolder> {

    List<EditAkunClass> eList;
    Context context;

    public EditAkunAdapter(List<EditAkunClass> eList, Context context) {
        this.eList = eList;
        this.context = context;
    }

    @NonNull
    @Override
    public EditAkunAdapter.EditAkunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.roweditakun, parent, false);

        return new EditAkunAdapter.EditAkunHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditAkunAdapter.EditAkunHolder holder, int position) {
        holder.judul.setText(eList.get(position).getJudul());
        holder.isi.setText(eList.get(position).getIsi());
    }

    @Override
    public int getItemCount() {
        return eList.size();
    }

    public class EditAkunHolder extends RecyclerView.ViewHolder {
        TextView judul, isi;

        public EditAkunHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            isi = itemView.findViewById(R.id.isi);
        }
    }
}
