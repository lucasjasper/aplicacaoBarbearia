package com.example.aplicacaobarbearia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CorteDAO {

    private ConexaoBD conexao;
    private SQLiteDatabase banco;

    public CorteDAO(Context context) {

        conexao = new ConexaoBD(context);
        banco = conexao.getWritableDatabase();

    }

    public void inserir(Corte corte) {

        ContentValues values = new ContentValues();

        values.put("descricao", corte.getDescricao());
        values.put("valor", corte.getValor());

        banco.insert("corte", null, values);

    }

    public List<Corte> consultaTodos() {

        List<Corte> cortes = new ArrayList<>();

        Cursor cursor = banco.query("corte", new String[] {"id", "descricao", "valor"},
                null, null, null, null, null);

        if (cursor.getCount() > 0) {

            while(cursor.moveToNext()) {

                Corte corte = new Corte();
                corte.setId(cursor.getInt(0));
                corte.setDescricao(cursor.getString(1));
                corte.setValor(cursor.getString(2));

                cortes.add(corte);

            }

            cursor.close();

        }

        return cortes;

    }

    public void atualizarCorte(Corte c) {

        ContentValues values = new ContentValues();

        values.put("descricao", c.getDescricao());
        values.put("valor", c.getValor());

        banco.update("corte", values, "id = ?", new String[] {c.getId().toString()});

    }

    public void excluirCorte(Corte c) {

        banco.delete("corte", "id = ?", new String[] {c.getId().toString()});

    }

}
