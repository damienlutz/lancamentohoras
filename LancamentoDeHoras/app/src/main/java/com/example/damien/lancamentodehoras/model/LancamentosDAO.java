package com.example.damien.lancamentodehoras.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.damien.lancamentodehoras.database.OpenHelperLancamentos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranciscoHenrique on 20/07/2015.
 */
public class LancamentosDAO {

    OpenHelperLancamentos bdOpenHelperLancamentos;

    public LancamentosDAO(Context contexto) {
        bdOpenHelperLancamentos = new OpenHelperLancamentos(contexto);
    }

    public boolean inserir(Lancamentos lancamentos) {
        SQLiteDatabase banco = bdOpenHelperLancamentos.getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("usuario", lancamentos.getUsuarioLancamento());
        dados.put("horas", lancamentos.getHorasLancamentos());
        boolean sucesso = banco.insert("lancamentos", null, dados) > 0;
        banco.close();
        return (sucesso);
    }

    public List<Lancamentos> getAll() {
        List<Lancamentos> listaLancamentos = new ArrayList<Lancamentos>();
        SQLiteDatabase banco = bdOpenHelperLancamentos.getReadableDatabase();
        Cursor cursor = banco.query("lancamentos",
                new String[]{"usuario", "horas"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Lancamentos produto = new Lancamentos(
                    cursor.getString(cursor.getColumnIndex("usuario")),
                    cursor.getString(cursor.getColumnIndex("horas")));
            listaLancamentos.add(produto);
        }
        return (listaLancamentos);
    }
}

