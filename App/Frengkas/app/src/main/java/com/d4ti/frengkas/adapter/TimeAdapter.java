package com.d4ti.frengkas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.model.Pukul;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private Context context;
    private List<Pukul> pukuls;

    public TimeAdapter(Context context) {
        this.context = context;
    }

    public List<Pukul> getPukuls() {
        return pukuls;
    }

    public void setPukuls(List<Pukul> pukuls) {
        this.pukuls = pukuls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_time, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_start.setText(getPukuls().get(i).getStart());
        viewHolder.tv_end.setText(getPukuls().get(i).getEnd());
        viewHolder.tv_status.setText(getPukuls().get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return pukuls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_start, tv_end, tv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_start = itemView.findViewById(R.id.txt_start);
            tv_end = itemView.findViewById(R.id.txt_end);
            tv_status = itemView.findViewById(R.id.txt_status);
        }
    }
}
