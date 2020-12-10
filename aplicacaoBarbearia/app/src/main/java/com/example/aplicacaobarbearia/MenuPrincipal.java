package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {

    ImageButton botaoCorte, botaoCliente, botaoServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        botaoCorte = (ImageButton) findViewById(R.id.buttonCortes);
        botaoCliente = (ImageButton) findViewById(R.id.buttonClientes);
        botaoServico = (ImageButton) findViewById(R.id.buttonServicos);

    }

    public void botaoCorte(View view){
        Intent it = new Intent(this, ListaCorte.class);
        startActivity(it);
    }

    public void botaoCliente(View view){
        Intent it = new Intent(this, ListaCliente.class);
        startActivity(it);
    }

    public void botaoServico(View view){
        Intent it = new Intent(this, ListaServico.class);
        startActivity(it);
    }

    public void botaoDesconectar(View view){
        Intent it = new Intent(this, Login.class);
        startActivity(it);
    }

}