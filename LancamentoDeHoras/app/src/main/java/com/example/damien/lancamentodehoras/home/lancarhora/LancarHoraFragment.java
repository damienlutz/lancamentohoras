package com.example.damien.lancamentodehoras.home.lancarhora;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.damien.lancamentodehoras.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LancarHoraFragment.} interface
 * to handle interaction events.
 * Use the {@link LancarHoraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LancarHoraFragment extends android.support.v4.app.Fragment {



    public static LancarHoraFragment newInstance() {
        LancarHoraFragment fragment = new LancarHoraFragment();
        Log.d(TAG, "log new inst");
        return fragment;
    }
    
    public LancarHoraFragment() {
        // Required empty public constructor
        Log.d(TAG, "construct");
    }
    private static final String TAG = "LancamentoDeHorasActivity - Lan";

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
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
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
