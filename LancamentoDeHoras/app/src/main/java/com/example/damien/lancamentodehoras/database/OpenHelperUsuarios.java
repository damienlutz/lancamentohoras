package com.example.damien.lancamentodehoras.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelperUsuarios extends SQLiteOpenHelper {
    private static int VERSAO = 3;
    private static String nome = "lancamentodehoras.db";
    private static String CREATE_USUARIOS = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT UNIQUE, nome TEXT, email TEXT UNIQUE, senha TEXT);";
    public static String INSERT_USUARIOS = "INSERT INTO usuarios (usuario, nome, email, senha) VALUES ('francisco.nelsis', 'Francisco Nelsis', 'francisco.nelsis@ilegra.com', '12345');";
    private static String DROP_USUARIOS = "DROP TABLE IF EXISTS usuarios;";

    public OpenHelperUsuarios(Context contexto) {
        super(contexto, nome, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(CREATE_USUARIOS);
        banco.execSQL(INSERT_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int versaoNova) {
        banco.execSQL(DROP_USUARIOS);
        onCreate(banco);
    }
}