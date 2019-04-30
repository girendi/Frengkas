package com.d4ti.frengkas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4ti.frengkas.DetailOrderActivity;
import com.d4ti.frengkas.R;
import com.d4ti.frengkas.admin.DetailOrderAdminActivity;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;
import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private Context context;
    private BaseService baseService;
    private List<Order> orders;

    public BookingAdapter(Context context) {
        this.context = context;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_booking, viewGroup, false);
        baseService = APIUtils.getApiService();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_status.setText(getOrders().get(i).getStatus());
        baseService.getDetailCategory(getOrders().get(i).getId_category()).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, final Response<Category> response) {
                if (response.isSuccessful()){
                    Category category = response.body();
                    baseService.getDetailService(category.getId_service()).enqueue(new Callback<Service>() {
                        @Override
                        public void onResponse(Call<Service> call, Response<Service> response) {
                            if (response.isSuccessful()){
                                Service service = response.body();
                                final String nameService = service.getName();
                                baseService.getDetailPukul(getOrders().get(i).getId_pukul()).enqueue(new Callback<Pukul>() {
                                    @Override
                                    public void onResponse(Call<Pukul> call, Response<Pukul> response) {
                                        if (response.isSuccessful()){
                                            Pukul pukul = response.body();
                                            baseService.getDetailWaktu(pukul.getId_waktu()).enqueue(new Callback<Waktu>() {
                                                @Override
                                                public void onResponse(Call<Waktu> call, Response<Waktu> response) {
                                                    Waktu waktu = response.body();
                                                    String nameWaktu = waktu.getDate();
                                                    viewHolder.tv_service.setText(nameService + " - " + nameWaktu);
                                                }

                                                @Override
                                                public void onFailure(Call<Waktu> call, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Pukul> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Service> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                t.printStackTrace();
            }
        });

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SaveSharedPreference.getLoggedStatus(context)){
                    if (SaveSharedPreference.getStatus(context).equals("customer")){
                        Intent intent = new Intent(context, DetailOrderActivity.class);
                        intent.putExtra("ID_ORDER", getOrders().get(i).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, DetailOrderAdminActivity.class);
                        intent.putExtra("ID_ORDER", getOrders().get(i).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_service, tv_status;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv_service = itemView.findViewById(R.id.txt_service);
            tv_status = itemView.findViewById(R.id.txt_status);
        }
    }
}
