package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditaServico extends AppCompatActivity {

    EditText editTextData, editTextCorte, editTextCliente;

    private Servico servico;
    private ServicoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_servico);

        editTextData = findViewById(R.id.editTextData);
        editTextCorte = findViewById(R.id.editTextCorte);
        editTextCliente = findViewById(R.id.editTextCliente);

        Intent it = getIntent();

        if (it.hasExtra("agenda")) {

            servico = (Servico) it.getSerializableExtra("agenda");

            editTextData.setText(servico.getData_corte());
            editTextCorte.setText(servico.getCorte());
            editTextCliente.setText(servico.getCliente());

        }

    }

    public void salvarServico(View view){

        if (servico != null) {

            servico.setData_corte(editTextData.getText().toString());
            servico.setCorte(editTextCorte.getText().toString());
            servico.setCliente(editTextCliente.getText().toString());

            dao = new ServicoDAO(this);
            dao.atualizarServico(servico);

            Mensagem("Agenda atualizada!");

        }

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}