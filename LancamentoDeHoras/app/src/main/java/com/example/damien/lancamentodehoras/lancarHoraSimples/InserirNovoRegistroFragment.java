package com.example.damien.lancamentodehoras.lancarHoraSimples;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.damien.lancamentodehoras.R;


import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class InserirNovoRegistroFragment extends Fragment {

    public InserirNovoRegistroFragment() {
    }

    private TextView statusContador;
    private TextView iniciadoContador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inserir_novo_registro, container, false);
        addListeners(view);
        statusContador = (TextView) view.findViewById(R.id.statusContador);
        iniciadoContador = (TextView) view.findViewById(R.id.iniciadoContador);
        return view;
    }

    public void addListeners(View view) {
        Button btIniciarContador = (Button) view.findViewById(R.id.btIniciarContador);
        btIniciarContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaContador();
            }
        });

        Button btPararContador = (Button) view.findViewById(R.id.btPararContador);
        btPararContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pararContador();
            }
        });

        Button btEnviar = (Button) view.findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia();
            }
        });

        Button btCancelar = (Button) view.findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancela();
            }
        });
    }


    public void iniciaContador() {
        iniciadoContador.setText("RODANDO");
        statusContador.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        //statusContador.setText("");
    }

    private void pararContador() {
        statusContador.setText("PARADO");
        iniciadoContador.setText("--:--:--");
        //Envia hora_final - hora_inicial para BD.
    }


    private void envia() {
    }

    public void cancela() {
        iniciadoContador.setText("--:--:--");
    }
}