package com.d4ti.frengkas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.d4ti.frengkas.MainActivity;
import com.d4ti.frengkas.R;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button btn_login;
    private EditText et_username, et_password;
    private View view;

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
                SaveSharedPreference.setLoggedIn(getActivity(), true);
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

    private void initComponent() {
        et_username = view.findViewById(R.id.txt_email);
        et_password = view.findViewById(R.id.txt_password);
        btn_login = view.findViewById(R.id.btn_login);
    }

}
