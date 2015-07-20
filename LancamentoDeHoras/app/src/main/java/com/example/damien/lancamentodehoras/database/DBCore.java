package com.example.damien.lancamentodehoras.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCore extends SQLiteOpenHelper {
    private static int VERSAO = 1;
    private static String nome = "lancamentodehoras.db";
    private static String create = "CREATE TABLE lancamento" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "usuario VARCHAR(30)," +
            "horas REAL);";

    public DBCore(Context contexto) {
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


//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.damien.lancamentodehoras.models.Lancamentos;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBCore extends SQLiteOpenHelper {
//    SQLiteDatabase abreBanco;
//    SQLiteOpenHelper openHelper;
//    private static final String NOME_BD = "lancamentodehoras.db";
//    private static final int VERSAO_BD = 1;
//    private static final String NOME_TABLE = "lancamentos";
//    private static final String CREATE_TABLE = "create table " + NOME_TABLE + "(_id integer primary key autoincrement, usuario text, horas text, projeto text, email text);";
//    private static final String DROP_TABLE = "drop table " + NOME_TABLE + ";";
//
//    //UMA CLASSE OPENHELPER PARA CADA TABELA
//    public DBCore(Context ctx) {
//        super(ctx, NOME_BD, null, VERSAO_BD);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase abreBanco) {
//        abreBanco.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
//        abreBanco.execSQL("drop table " + NOME_TABLE + ";");
//        onCreate(abreBanco);
//    }
//
//    public void inserir(Lancamentos lancamentos) {
//        abreBanco = SQLiteDatabase.openOrCreateDatabase(NOME_BD, null, null);
//        abreBanco = openHelper.getWritableDatabase();
//        ContentValues valores = new ContentValues();
//        valores.put("usuario", lancamentos.getUsuarioLancamento());
//        valores.put("horas", lancamentos.getHorasLancamentos());
//        //valores.put("projeto", lancamentos.getProjetoLancamento());
//        //valores.put("email", lancamentos.getEmailLancamento());
//        abreBanco.insert("lancamentos", null, valores);
//    }
//
//    public List<Lancamentos> buscar() {
//        abreBanco = openHelper.getReadableDatabase();
//        List<Lancamentos> list = new ArrayList<Lancamentos>();
//        String[] colunas = new String[]{"_id", "usuario", "horas", "projeto", "email"};
//
//        Cursor cursor = abreBanco.query("lancamentos", colunas, null, null, null, null, "usuario ASC");
//
//        //////logica da busca
//
//        return (list);
//    }
//}


