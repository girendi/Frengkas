package com.d4ti.frengkas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4ti.frengkas.AgeActivity;
import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Context context;
    private List<Service> services;
    private BaseService baseService;
    private List<Category> categories;

    public ServiceAdapter(Context context) {
        this.context = context;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_service, viewGroup, false);
        baseService = APIUtils.getApiService();
        categories = new ArrayList<>();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.txt_service.setText(getServices().get(i).getName());
        viewHolder.txt_desc.setText(getServices().get(i).getDesc());

        baseService.getCategory(getServices().get(i).getId()).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    categories = response.body().getCategories();
                    if (!categories.isEmpty()){
                        double tempMax = categories.get(0).getPrice();
                        double tempMin = categories.get(0).getPrice();
                        for (int i = 0; i < categories.size(); i++){
                            if (categories.get(i).getPrice() >= tempMax){
                                tempMax = categories.get(i).getPrice();
                            }

                            if (categories.get(i).getPrice() <= tempMin){
                                tempMin = categories.get(i).getPrice();
                            }

                            viewHolder.txt_price.setText("Rp " + tempMin + " - " + "Rp " + tempMax);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("Error Message", t.getMessage());
            }
        });

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(context.getApplicationContext(), AgeActivity.class);
                nextIntent.putExtra("ID_SERVICE", getServices().get(i).getId());
                nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(nextIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_service;
        TextView txt_price;
        TextView txt_desc;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            txt_service = itemView.findViewById(R.id.txt_service);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_desc = itemView.findViewById(R.id.txt_desc);
        }
    }
}
