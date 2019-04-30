package com.d4ti.frengkas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.admin.time.DetailDateTimeActivity;
import com.d4ti.frengkas.model.Waktu;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private Context context;
    private List<Waktu> waktus;

    public DateAdapter(Context context) {
        this.context = context;
    }

    public List<Waktu> getWaktus() {
        return waktus;
    }

    public void setWaktus(List<Waktu> waktus) {
        this.waktus = waktus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_waktu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_date.setText(getWaktus().get(i).getDate());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDateTimeActivity.class);
                intent.putExtra("ID_WAKTU", getWaktus().get(i).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return waktus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv_date = itemView.findViewById(R.id.txt_date);
        }
    }
}
