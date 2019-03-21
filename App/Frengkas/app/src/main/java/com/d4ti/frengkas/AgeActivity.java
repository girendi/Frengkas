package com.d4ti.frengkas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;
import com.d4ti.frengkas.response.CategoryResponse;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgeActivity extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rbA, rbR, rbD;
    private Button btnOk;
    private List<Category> categories;
    private BaseService baseService;
    private int id_service, idA, idR, idD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        id_service = getIntent().getIntExtra("ID_SERVICE", 0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Pilih Usia");

        initComponent();
        setData();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())){
                    getOption();
                }else {
                    startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                    finish();
                }

            }
        });
    }

    private void getOption() {
        if (rbA.isChecked()){
            Intent nextIntent = new Intent(this, LocationActivity.class);
            nextIntent.putExtra("ID_CATEGORY", idA);
            startActivity(nextIntent);
        }else if (rbR.isChecked()){
            Intent nextIntent = new Intent(this, LocationActivity.class);
            nextIntent.putExtra("ID_CATEGORY", idR);
            startActivity(nextIntent);
        }else if (rbD.isChecked()){
            Intent nextIntent = new Intent(this, LocationActivity.class);
            nextIntent.putExtra("ID_CATEGORY", idD);
            startActivity(nextIntent);
        }else{
            Toast.makeText(this, "Silahkan Tentukan Pilihan Anda", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        rg.clearCheck();
        baseService.getCategory(id_service).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    categories = response.body().getCategories();
                    if (!categories.isEmpty()){
                        if (categories.size() <= 1){
                            idA = categories.get(0).getId();
                            rbA.setText(categories.get(0).getName()+ " \t\t " + categories.get(0).getPrice());
                            rbR.setVisibility(View.INVISIBLE);
                            rbD.setVisibility(View.INVISIBLE);
                        }else if (categories.size() <= 2){
                            idA = categories.get(0).getId();
                            idR = categories.get(1).getId();
                            rbA.setText(categories.get(0).getName()+ " \t\t " + categories.get(0).getPrice());
                            rbR.setText(categories.get(1).getName()+ " \t\t " + categories.get(1).getPrice());
                            rbD.setVisibility(View.INVISIBLE);
                        }else{
                            idA = categories.get(0).getId();
                            idR = categories.get(1).getId();
                            idD = categories.get(2).getId();
                            rbA.setText(categories.get(0).getName()+ " \t\t " + categories.get(0).getPrice());
                            rbR.setText(categories.get(1).getName()+ " \t\t " + categories.get(1).getPrice());
                            rbD.setText(categories.get(2).getName()+ " \t\t " + categories.get(2).getPrice());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("Error", "onFailure: " + t.getMessage());
            }
        });
    }

    private void initComponent() {
        rg = findViewById(R.id.rg);
        rbA = findViewById(R.id.rb_anakanak);
        rbR = findViewById(R.id.rb_remaja);
        rbD = findViewById(R.id.rb_Dewasa);
        btnOk = findViewById(R.id.btn_ok);
        categories = new ArrayList<>();
        baseService = APIUtils.getApiService();
    }


}
