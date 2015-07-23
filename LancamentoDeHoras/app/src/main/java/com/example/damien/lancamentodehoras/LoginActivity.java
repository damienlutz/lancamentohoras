package com.example.damien.lancamentodehoras;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damien.lancamentodehoras.database.OpenHelperUsuarios;
import com.example.damien.lancamentodehoras.home.HomeActivity;
import com.example.damien.lancamentodehoras.model.Usuarios;
import com.example.damien.lancamentodehoras.model.UsuariosDAO;


public class LoginActivity extends FragmentActivity {

    UsuariosDAO usuariosDAO = new UsuariosDAO(getBaseContext());
    Usuarios usuarios = new Usuarios();

    private static final String TAG = "LoginActivity";

    public void login() {
        String usuarioUsuario = String.valueOf(((EditText) findViewById(R.id.username)).getText());
        String senhaUsuario = String.valueOf(((EditText) findViewById(R.id.password)).getText());


//        if (usuarioUsuario.length() == 0 || senhaUsuario.length() == 0) {
//            Toast.makeText(this, "Preencha nome de usuário e senha para acessar!", Toast.LENGTH_SHORT).show();
//        } else {
//            usuariosDAO.validaUsuario(usuarioUsuario, senhaUsuario);
//
//            if (usuariosDAO.usuarioValidado) {
        usuarios.setUsuarioUsuario(usuarioUsuario);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
//            } else {
//                Toast.makeText(this, "Usuário/senha incorreto(a)!", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_de_horas);

        Button btnLogin = (Button) findViewById(R.id.btLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lancamento_de_horas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
