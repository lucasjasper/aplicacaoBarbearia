package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditaCliente extends AppCompatActivity {

    EditText editTextNome, editTextTelefone;

    private Cliente cliente;
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_cliente);

        editTextNome = findViewById(R.id.editTextNome);
        editTextTelefone = findViewById(R.id.editTextTelefone);

        Intent it = getIntent();

        if (it.hasExtra("cliente")) {

            cliente = (Cliente) it.getSerializableExtra("cliente");

            editTextNome.setText(cliente.getNome());
            editTextTelefone.setText(cliente.getTelefone());

        }

    }

    public void salvarCliente(View view){

        if (cliente != null) {

            cliente.setNome(editTextNome.getText().toString());
            cliente.setTelefone(editTextTelefone.getText().toString());

            dao = new ClienteDAO(this);
            dao.atualizarCliente(cliente);

            Mensagem("Cliente atualizado!");

        }

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}