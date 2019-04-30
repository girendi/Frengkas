package com.d4ti.frengkas.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;
import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.model.Waktu;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderAdminActivity extends AppCompatActivity {

    private TextView txtName, txtLayanan, txtRentang, txtLokasi, txtWaktu, txtPukul, txtBiaya, txtStatus;
    private Button btnDecline, btnAgree;
    private int id_order;
    private BaseService baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Order");
        initComponent();
        setData();
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setData() {
        baseService.getDetailOrder(id_order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()){
                    Order order = response.body();
                    txtLokasi.setText(order.getLocation());
                    txtStatus.setText(order.getStatus());
                    baseService.getDetailCategory(order.getId_category()).enqueue(new Callback<Category>() {
                        @Override
                        public void onResponse(Call<Category> call, Response<Category> response) {
                            if (response.isSuccessful()){
                                final Category category = response.body();
                                txtRentang.setText(category.getName());
                                txtBiaya.setText(Double.toString(category.getPrice()));
                                baseService.getDetailService(category.getId_service()).enqueue(new Callback<Service>() {
                                    @Override
                                    public void onResponse(Call<Service> call, Response<Service> response) {
                                        if (response.isSuccessful()){
                                            Service service = response.body();
                                            txtLayanan.setText(service.getName());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Service> call, Throwable t) {
                                        Log.e("Error Service", t.getMessage());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Category> call, Throwable t) {
                            Log.e("Error Category", t.getMessage());
                        }
                    });
                    baseService.getDetailPukul(order.getId_pukul()).enqueue(new Callback<Pukul>() {
                        @Override
                        public void onResponse(Call<Pukul> call, Response<Pukul> response) {
                            if (response.isSuccessful()){
                                Pukul pukul = response.body();
                                txtPukul.setText(pukul.getStart() + " - " + pukul.getEnd());

                                baseService.getDetailWaktu(pukul.getId_waktu()).enqueue(new Callback<Waktu>() {
                                    @Override
                                    public void onResponse(Call<Waktu> call, Response<Waktu> response) {
                                        if (response.isSuccessful()){
                                            Waktu waktu = response.body();
                                            txtWaktu.setText(waktu.getDate());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Waktu> call, Throwable t) {
                                        Log.e("Error Waktu", t.getMessage());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Pukul> call, Throwable t) {
                            Log.e("Error Pukul", t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Error Message Get Data", t.getMessage());
            }
        });
    }

    private void initComponent() {
        id_order = getIntent().getIntExtra("ID_ORDER", 0);

        //view
        baseService = APIUtils.getApiService();

        txtName = findViewById(R.id.txt_name);
        txtLayanan = findViewById(R.id.txt_layanan);
        txtRentang = findViewById(R.id.txt_rentang);
        txtLokasi = findViewById(R.id.txt_lokasi);
        txtWaktu = findViewById(R.id.txt_waktu);
        txtPukul = findViewById(R.id.txt_pukul);
        txtBiaya = findViewById(R.id.txt_total);
        txtStatus = findViewById(R.id.txt_status);
        btnDecline = findViewById(R.id.btn_decline);
        btnAgree = findViewById(R.id.btn_agree);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
