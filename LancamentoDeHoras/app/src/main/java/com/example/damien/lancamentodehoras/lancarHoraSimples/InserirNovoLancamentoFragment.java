package com.example.damien.lancamentodehoras.lancarHoraSimples;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
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
public class InserirNovoLancamentoFragment extends Fragment {

    //TODO Linha com erro abaixo
    //private LancarHoraWS lancarHoraWS = new LancarHoraWS();

    private LancarHoraWS ws;

    public InserirNovoLancamentoFragment() {
    }

    private TextView statusContador;
    private TextView horaInicial = null;
    private TextView horaFinal = null;
    private Button btAtividade;
    private AlertDialog alerta;
    String horasRegistradas;
    boolean flagStatusContadorRodando = false;
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
        horaInicial = (TextView) view.findViewById(R.id.horaInicial);
        horaFinal = (TextView) view.findViewById(R.id.horaFinal);
        btAtividade = (Button) view.findViewById(R.id.btAtividade);
        ws = new LancarHoraWS(getActivity());
        return view;
    }

    public void addListeners(View view) {
        final TextView btAtividade = (TextView) view.findViewById(R.id.btAtividade);
        btAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagStatusContadorRodando == false) {
                    iniciaContador();
                } else if (flagStatusContadorRodando == true) {
                    pararContador();
                }
            }
        });

        Button btEnviar = (Button) view.findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagStatusContadorRodando == false && horaFinal.getText() != "" && horaFinal.getText() != "") {

                    //EditText campoUsuario = (EditText) getActivity().findViewById(R.id.statusContador);
                    String campoUsuario = "Funcionou!"; //MOCK
                    //TODO Pegar Usuário passado da tela de login por intent

                    Lancamentos lancamentos = new Lancamentos(campoUsuario, horasRegistradas);
                    LancamentosDAO dao = new LancamentosDAO(getActivity());
                    dao.inserir(lancamentos);
                    Toast.makeText(getActivity(), "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    flagStatusContadorRodando = false;
                    statusContador.setText("PARADO");
                    horaInicial.setText("");
                    horaFinal.setText("");
                } else if (flagStatusContadorRodando == false && horasRegistradas == null) {
                    Toast.makeText(getActivity(), "Você precisa logar horas antes de enviar!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Finalize a sessão de trabalho antes de Enviar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btLimpar = (Button) view.findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });
    }

    public void iniciaContador() {
        flagStatusContadorRodando = true;
        statusContador.setText("RODANDO");
        horaInicial.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        btAtividade.setText("FINALIZAR");
        startTimerThread();
    }

    private void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            private long startTime = System.currentTimeMillis();

            public void run() {
                while (flagStatusContadorRodando == true && horaInicial != null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            timer();
                        }
                    });
                }
            }
        };
//        new Thread(runnable).start();
    }

    private void pararContador() {
        flagStatusContadorRodando = false;
        statusContador.setText("PARADO");
        horaFinal.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        btAtividade.setText("INICIAR");
    }

    private void timer() {
        horaFinal.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
    }

    public void limpar() {
        if (flagStatusContadorRodando) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmação");
            builder.setMessage("Cancelar o contador?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    flagStatusContadorRodando = false;
                    Toast.makeText(getActivity(), "Contador cancelado.", Toast.LENGTH_SHORT).show();
                    statusContador.setText("PARADO");
                    horaInicial.setText("");
                    horaFinal.setText("");
                    btAtividade.setText("INICIAR");
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
        } else if (flagStatusContadorRodando == false && horaInicial.getText() != "") {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmação");
            builder.setMessage("Você tem horas registradas. Deseja cancelar o contador?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Horas canceladas.", Toast.LENGTH_SHORT).show();
                    statusContador.setText("PARADO");
                    horaInicial.setText("");
                    horaFinal.setText("");
                    btAtividade.setText("INICIAR");
                    horasRegistradas = "";
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
        } else {
        }
    }
}