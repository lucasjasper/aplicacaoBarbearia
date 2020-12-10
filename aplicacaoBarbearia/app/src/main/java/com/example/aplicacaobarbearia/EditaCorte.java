package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditaCorte extends AppCompatActivity {

    EditText editTextDescricao, editTextValor;

    private Corte corte;
    private CorteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_corte);

        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextValor = findViewById(R.id.editTextValor);

        Intent it = getIntent();

        if (it.hasExtra("corte")) {

            corte = (Corte) it.getSerializableExtra("corte");

            editTextDescricao.setText(corte.getDescricao());
            editTextValor.setText(corte.getValor());

        }

    }

    public void salvarCorte(View view){

        if (corte != null) {

            corte.setDescricao(editTextDescricao.getText().toString());
            corte.setValor(editTextValor.getText().toString());

            dao = new CorteDAO(this);
            dao.atualizarCorte(corte);

            Mensagem("Corte atualizado!");

        }

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}