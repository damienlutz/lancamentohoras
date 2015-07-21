package com.example.damien.lancamentodehoras.lancarHoraSimples;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.damien.lancamentodehoras.R;
import com.example.damien.lancamentodehoras.model.Lancamentos;
import com.example.damien.lancamentodehoras.model.LancamentosDAO;
import com.example.damien.lancamentodehoras.ws.LancarHoraWS;

public class ConsultarLancamentosFragment extends ListFragment {
    List<Lancamentos> listaLancamentos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v4.app.FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            ConsultarLancamentosFragment list = new ConsultarLancamentosFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }

        //getActivity().setContentView(R.layout.fragment_consultar_lancamentos);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateLista();
    }

    public void updateLista() {
        List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

        LancamentosDAO dao = new LancamentosDAO(getActivity());
        listaLancamentos = dao.getAll();

        HashMap<String, String> mapHeader = new HashMap<String, String>();
        mapHeader.put("usuario", "USUARIO");
        mapHeader.put("horas", "HORAS");

        lista.add(mapHeader);

        for (Lancamentos lancamentos : listaLancamentos) {
            HashMap<String, String> map = new HashMap<String, String>();

            map.put("usuario", String.valueOf(lancamentos.getUsuarioLancamento()));
            map.put("horas", String.valueOf(lancamentos.getHorasLancamentos()));

            lista.add(map);
        }

        String from[] = {"nome", "horas"};
        int to[] = {R.id.textoListarNome, R.id.textoListarHoras};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), lista, R.layout.fragment_consultar_lancamentos, from, to);
        setListAdapter(adapter);
    }


}
