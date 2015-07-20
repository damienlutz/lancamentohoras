package com.example.damien.lancamentodehoras.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelperLancamento extends SQLiteOpenHelper {
    private static int VERSAO = 1;
    private static String nome = "lancamentodehoras.db";
    private static String create = "CREATE TABLE lancamento" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "usuario VARCHAR(30)," +
            "horas REAL);";

    public OpenHelperLancamento(Context contexto) {
        super(contexto, nome, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int versaoNova) {

    }


}