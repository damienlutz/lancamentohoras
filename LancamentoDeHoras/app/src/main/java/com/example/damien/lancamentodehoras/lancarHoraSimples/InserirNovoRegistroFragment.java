package com.example.damien.lancamentodehoras.lancarHoraSimples;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damien.lancamentodehoras.R;

import com.example.damien.lancamentodehoras.database.OpenHelperLancamento;
import com.example.damien.lancamentodehoras.model.Lancamentos;

import com.example.damien.lancamentodehoras.model.LancamentosDAO;
import com.example.damien.lancamentodehoras.ws.LancarHoraWS;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class InserirNovoRegistroFragment extends Fragment {

    //TODO Linha com erro abaixo
    //private LancarHoraWS lancarHoraWS = new LancarHoraWS();

    private LancarHoraWS ws;

    public InserirNovoRegistroFragment() {
    }

    private TextView statusContador;
    private TextView iniciadoContador;
    private Button btAtividade;
    private AlertDialog alerta;
    String horasRegistradas;
    OpenHelperLancamento db;

    String usuario = "DEU CERTO!";
    String horas;
    EditText projeto;
    String email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_inserir_novo_registro, container, false);

        addListeners(view);
        statusContador = (TextView) view.findViewById(R.id.statusContador);
        iniciadoContador = (TextView) view.findViewById(R.id.iniciadoContador);
        btAtividade = (Button) view.findViewById(R.id.btAtividade);

        ws = new LancarHoraWS(getActivity());
        return view;
    }

    public void addListeners(View view) {
        final TextView btAtividade = (TextView) view.findViewById(R.id.btAtividade);
        btAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btAtividade.getText().equals("INICIAR")) {
                    iniciaContador();
                } else if (btAtividade.getText().equals("FINALIZAR")) {
                    pararContador();
                }
            }
        });

        Button btEnviar = (Button) view.findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btAtividade.getText().equals("INICIAR") && (horasRegistradas != null)) {

                    //EditText campoUsuario = (EditText) getActivity().findViewById(R.id.statusContador);
                    String campoUsuario = "Funcionou!"; //MOCK
                    //TODO Pegar Usuário passado da tela de login por intent

                    Lancamentos lancamentos = new Lancamentos(campoUsuario, horasRegistradas);
                    LancamentosDAO dao = new LancamentosDAO(getActivity());
                    dao.inserir(lancamentos);
                    Toast.makeText(getActivity(), "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    statusContador.setText("PARADO");
                    iniciadoContador.setText("--:--:--");
                } else if (btAtividade.getText().equals("INICIAR") && (horasRegistradas == null)) {
                    Toast.makeText(getActivity(), "Você precisa logar horas antes de enviar!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Finalize a sessão de trabalho antes de Enviar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btCancelar = (Button) view.findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
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
        horasRegistradas = getText(R.id.iniciadoContador).toString();
        btAtividade.setText("INICIAR");
    }

    public void limpar() {
        if (btAtividade.getText().equals("FINALIZAR")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmação");
            builder.setMessage("Cancelar o contador?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Contador cancelado.", Toast.LENGTH_SHORT).show();
                    statusContador.setText("PARADO");
                    iniciadoContador.setText("--:--:--");
                    btAtividade.setText("INICIAR");
                    horasRegistradas = null;
                }
            });
            builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.dismiss();
                }
            });
            alerta = builder.create();
            alerta.show();
        } else if (statusContador.getText().equals("PARADO") &&(iniciadoContador.getText() != ("--:--:--"))){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmação");
            builder.setMessage("Você tem horas registradas. Deseja cancelar o contador?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Horas canceladas.", Toast.LENGTH_SHORT).show();
                    statusContador.setText("PARADO");
                    iniciadoContador.setText("--:--:--");
                    btAtividade.setText("INICIAR");
                    horasRegistradas = null;
                }
            });
            builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.dismiss();
                }
            });
            alerta = builder.create();
            alerta.show();
        }else{

        }
    }
}