package com.d4ti.frengkas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText et_name, et_email, et_pass, et_confir, et_address, et_phone;
    private TextView tv_login;
    private Button btn_register;
    private View view;
    private BaseService baseService;
    private String pass, confPass;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);

        initComponent();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = et_pass.getText().toString();
                confPass = et_confir.getText().toString();
                if (pass.equals(confPass)){
                    registerRequest();
                }else{
                    Toast.makeText(getActivity(), "Password Harus Sama", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        return view;
    }

    private void registerRequest() {
        baseService.registerRequest(et_name.getText().toString(), et_email.getText().toString(), pass, et_address.getText().toString(), et_phone.getText().toString())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            goToLogin();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("Error Message", t.getMessage());
                    }
                });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        et_address = view.findViewById(R.id.txt_alamat);
        et_phone = view.findViewById(R.id.txt_no);
        et_name = view.findViewById(R.id.txt_name);
        et_email = view.findViewById(R.id.txt_email);
        et_pass = view.findViewById(R.id.txt_password);
        et_confir = view.findViewById(R.id.txt_confir_password);
        btn_register = view.findViewById(R.id.btn_register);
        tv_login = view.findViewById(R.id.tv_login);
    }

    private void goToLogin() {
        Fragment fragment = new LoginFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_auth, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
