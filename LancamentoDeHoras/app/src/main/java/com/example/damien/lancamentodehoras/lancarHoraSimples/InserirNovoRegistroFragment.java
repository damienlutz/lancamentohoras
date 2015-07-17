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
    private Button btAtividade;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inserir_novo_registro, container, false);
        addListeners(view);
        statusContador = (TextView) view.findViewById(R.id.statusContador);
        iniciadoContador = (TextView) view.findViewById(R.id.iniciadoContador);
        btAtividade = (Button) view.findViewById(R.id.btAtividade);
        return view;
    }

    public void addListeners(View view) {
        final Button btAtividade = (Button) view.findViewById(R.id.btAtividade);
        btAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btAtividade.getText().equals("INICIAR")) {
                    iniciaContador();
                } else if (btAtividade.getText().equals("FINALIZAR")){
                    pararContador();
                }
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
        statusContador.setText("RODANDO");
        iniciadoContador.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        btAtividade.setText("FINALIZAR");
    }

    private void pararContador() {
        statusContador.setText("PARADO");
        iniciadoContador.setText("--:--:--");
        btAtividade.setText("INICIAR");
    }

    private void envia() {

    }

    public void cancela() {
        statusContador.setText("PARADO");
        iniciadoContador.setText("--:--:--");
        btAtividade.setText("INICIAR");
    }
}