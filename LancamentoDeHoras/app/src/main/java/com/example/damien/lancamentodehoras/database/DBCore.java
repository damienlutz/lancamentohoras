package com.example.damien.lancamentodehoras.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.damien.lancamentodehoras.models.Lancamentos;

import java.util.ArrayList;
import java.util.List;

public class DBCore extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String NOME_BD = "lancamentodehoras.db";
    private static final int VERSAO_BD = 1;
    private static final String NOME_TABLE = "lancamentos";
    private static final String CREATE_TABLE = "create table " + NOME_TABLE + "(_id integer primary key autoincrement, usuario text, horas text, projeto text, email text);";
    private static final String DROP_TABLE = "drop table " + NOME_TABLE + ";";


    public DBCore(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db = this.getWritableDatabase();

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table " + NOME_TABLE + ";");
        onCreate(bd);
    }

    public void inserir(Lancamentos lancamentos) {
        ContentValues valores = new ContentValues();
        valores.put("usuario", lancamentos.getUsuarioLancamento());
        valores.put("horas", lancamentos.getHorasLancamentos());
        //valores.put("projeto", lancamentos.getProjetoLancamento());
        //valores.put("email", lancamentos.getEmailLancamento());
        db.insert("lancamentos", null, valores);
    }

    public List<Lancamentos> buscar() {
        List<Lancamentos> list = new ArrayList<Lancamentos>();
        String[] colunas = new String[]{"_id", "usuario", "horas", "projeto", "email"};

        Cursor cursor = db.query("lancamentos", colunas, null, null, null, null, "usuario ASC");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Lancamentos u = new Lancamentos();
                u.set_id(cursor.getLong(0));
                u.setUsuarioLancamento(cursor.getString(1));
                u.setHorasLancamentos(cursor.getString(2));
                u.setProjetoLancamento(cursor.getString(3));
                u.setEmailLancamento(cursor.getString(4));
                list.add(u);

            } while (cursor.moveToNext());
        }
        return (list);
    }
}


