package com.example.isomanhealthcare;

import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PanduanAdapter extends RecyclerView.Adapter<PanduanAdapter.PanduanPH> {

    List<PanduanAccordion> panduanList;
    Context context;

    public PanduanAdapter(List<PanduanAccordion> panduanList, Context context) {
        this.panduanList = panduanList;
        this.context = context;
    }

    @NonNull
    @Override
    public PanduanPH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rowpanduan, parent, false);
        return new PanduanPH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanduanPH holder, int position) {
//        PanduanAccordion panduanAccordion = panduanList.get(position);
        holder.panduanTxt.setText(panduanList.get(position).gettPanduan());
        holder.detailPanduanTxt.setText(Html.fromHtml(panduanList.get(position).getdPanduan()));

        boolean isExpandable = panduanList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return panduanList.size();
    }

    public class PanduanPH extends RecyclerView.ViewHolder {

        TextView panduanTxt, detailPanduanTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public PanduanPH(@NonNull View itemView) {
            super(itemView);

            panduanTxt = itemView.findViewById(R.id.panduan);
            detailPanduanTxt = itemView.findViewById(R.id.detailpanduan);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PanduanAccordion panduanAccordion = panduanList.get(getAdapterPosition());
                    panduanAccordion.setExpandable(!panduanAccordion.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
