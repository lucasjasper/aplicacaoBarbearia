package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText editTextUsuario, editTextSenha;

    String usuario, senha;

    int validaLogin;

    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);

    }

    public void botaoLogin (View view){

        usuario = editTextUsuario.getText().toString();
        senha = editTextSenha.getText().toString();

        dao = new UsuarioDAO(this);
        validaLogin = dao.buscaIdUsuario(usuario, senha);

        if (validaLogin != 0) {

            Intent it = new Intent(Login.this, MenuPrincipal.class);
            startActivity(it);

        } else {

            Mensagem("Usuário ou senha inválidos.");

        }

    }

    public void botaoCadastro (View view){

        Intent it = new Intent(Login.this, CadastraUsuario.class);
        startActivity(it);

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}