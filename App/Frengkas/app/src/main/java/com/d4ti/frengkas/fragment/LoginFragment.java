package com.d4ti.frengkas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.d4ti.frengkas.MainActivity;
import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.User;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button btn_login;
    private EditText et_username, et_password;
    private View view;
    private BaseService baseService;
    private User user;
    private int id;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initComponent();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });

        return view;
    }

    private void loginRequest() {
        baseService.loginRequest(et_username.getText().toString(), et_password.getText().toString())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            user = response.body();
                            assert user != null;
                            id = user.getId();
                            SaveSharedPreference.setLoggedIn(getActivity(), true);
                            SaveSharedPreference.setIdUser(getActivity(), id);
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            Objects.requireNonNull(getActivity()).finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("Error Message", t.getMessage());
                    }
                });
    }

    private void initComponent() {
        user = new User();
        baseService = APIUtils.getApiService();
        et_username = view.findViewById(R.id.txt_email);
        et_password = view.findViewById(R.id.txt_password);
        btn_login = view.findViewById(R.id.btn_login);
    }


}
