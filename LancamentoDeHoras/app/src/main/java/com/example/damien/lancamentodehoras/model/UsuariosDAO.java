package com.example.damien.lancamentodehoras.model;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.damien.lancamentodehoras.database.OpenHelperUsuarios;
import com.example.damien.lancamentodehoras.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranciscoHenrique on 22/07/2015.
 */
public class UsuariosDAO {

    public boolean usuarioValidado = false;

    OpenHelperUsuarios bdOpenHelperUsuarios;


    public UsuariosDAO(Context contexto) {
        bdOpenHelperUsuarios = new OpenHelperUsuarios(contexto);
    }

    public boolean validaUsuario(String usuarioUsuario, String senhaUsuario) {
        SQLiteDatabase banco = bdOpenHelperUsuarios.getReadableDatabase();
        Cursor cursor = banco.query("usuarios", null, "usuario=?", new String[]{usuarioUsuario}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();

        }
        cursor.moveToFirst();
        //TODO Verificar porque não está retornando registros na query.
        String senhaRetornada = cursor.getString(cursor.getColumnIndex("senha"));
        cursor.close();

        if (senhaRetornada.equals(senhaUsuario)) {
            usuarioValidado = true;
        } else {
            usuarioValidado = false;
        }
        return usuarioValidado;
    }
}