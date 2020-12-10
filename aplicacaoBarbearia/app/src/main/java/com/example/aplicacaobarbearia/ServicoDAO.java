package com.example.aplicacaobarbearia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private ConexaoBD conexao;
    private SQLiteDatabase banco;

    public ServicoDAO(Context context) {

        conexao = new ConexaoBD(context);
        banco = conexao.getWritableDatabase();

    }

    public void inserir(Servico servico) {

        ContentValues values = new ContentValues();

        values.put("data_corte", servico.getData_corte());
        values.put("corte", servico.getCorte());
        values.put("cliente", servico.getCliente());

        banco.insert("agenda", null, values);

    }

    public List<Servico> consultaTodos() {

        List<Servico> servicos = new ArrayList<>();

        Cursor cursor = banco.query("agenda", new String[] {"id", "data_corte", "corte", "cliente"},
                null, null, null, null, null);

        if (cursor.getCount() > 0) {

            while(cursor.moveToNext()) {

                Servico servico = new Servico();
                servico.setId(cursor.getInt(0));
                servico.setData_corte(cursor.getString(1));
                servico.setCorte(cursor.getString(2));
                servico.setCliente(cursor.getString(3));

                servicos.add(servico);

            }

            cursor.close();

        }

        return servicos;

    }

    public void atualizarServico(Servico s) {

        ContentValues values = new ContentValues();

        values.put("data_corte", s.getData_corte());
        values.put("corte", s.getCorte());
        values.put("cliente", s.getCliente());

        banco.update("agenda", values, "id = ?", new String[] {s.getId().toString()});

    }

    public void excluirServico(Servico s) {

        banco.delete("agenda", "id = ?", new String[] {s.getId().toString()});

    }

}
