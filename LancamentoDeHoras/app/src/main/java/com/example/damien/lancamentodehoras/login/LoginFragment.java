package com.example.damien.lancamentodehoras.login;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.damien.lancamentodehoras.R;
import com.example.damien.lancamentodehoras.database.OpenHelperUsuarios;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

}
