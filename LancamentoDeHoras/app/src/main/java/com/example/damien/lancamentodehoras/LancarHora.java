package com.example.damien.lancamentodehoras;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LancarHora.} interface
 * to handle interaction events.
 * Use the {@link LancarHora#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LancarHora extends android.support.v4.app.Fragment {



    public static LancarHora newInstance() {
        LancarHora fragment = new LancarHora();
        Log.d(TAG, "log new inst");
        return fragment;
    }
    
    public LancarHora() {
        // Required empty public constructor
        Log.d(TAG, "construct");
    }
    private static final String TAG = "LancamentoDeHoras - Lan";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "oncreate ");
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated ( savedInstanceState);
        Button button = (Button) getView().findViewById(R.id.button_pick_time);
        Log.d(TAG, "onActivityCreated ");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.d(TAG, "click");
            }
    });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lancar_hora, container, false);
    }





}
