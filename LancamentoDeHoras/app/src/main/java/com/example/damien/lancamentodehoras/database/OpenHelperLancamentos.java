package com.example.damien.lancamentodehoras.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelperLancamentos extends SQLiteOpenHelper {
    private static int VERSAO = 1;
    private static String nome = "lancamentodehoras.db";
    private static String create_lancamentos = "CREATE TABLE lancamentos(_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario VARCHAR(30), horas REAL);";


    public OpenHelperLancamentos(Context contexto) {
        super(contexto, nome, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(create_lancamentos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int versaoNova) {

    }
}