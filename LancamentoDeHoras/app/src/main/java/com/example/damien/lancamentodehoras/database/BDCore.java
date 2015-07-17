package com.example.damien.lancamentodehoras.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "lancamentodehoras";
    private static final int VERSAO_BD = 7;
    private static final String NOME_TABLE = "lancamentos";
    private static final String CREATE_TABLE = "create table "+NOME_TABLE+"(_id integer primary key autoincrement, usuario text not null, horas text not null, projeto text not null, email text not null);";
    private static final String DROOP_TABLE = "drop table "+NOME_TABLE+";";


    public BDCore(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table "+NOME_TABLE+";");
        onCreate(bd);
    }

}
