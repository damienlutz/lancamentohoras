package com.example.damien.lancamentodehoras.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.damien.lancamentodehoras.database.OpenHelperLancamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranciscoHenrique on 20/07/2015.
 */
public class LancamentosDAO {

    OpenHelperLancamento bdOpenHelper;

    public LancamentosDAO(Context contexto) {
        bdOpenHelper = new OpenHelperLancamento(contexto);
    }

    public boolean inserir(Lancamentos lancamentos) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("usuario", lancamentos.getUsuarioLancamento());
        dados.put("horas", lancamentos.getHorasLancamentos());
        boolean sucesso = banco.insert("lancamentos", null, dados) > 0;
        banco.close();
        return (sucesso);
    }

    public List<Lancamentos> getAll() {
        List<Lancamentos> listaProdutos = new ArrayList<Lancamentos>();

        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();
        Cursor cursor = banco.query("lancamentos",
                new String[]{"usuario", "horas"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Lancamentos produto = new Lancamentos(
                    cursor.getString(cursor.getColumnIndex("usuario")),
                    cursor.getString(cursor.getColumnIndex("horas")));
            listaProdutos.add(produto);
        }
        return (listaProdutos);
    }
}

