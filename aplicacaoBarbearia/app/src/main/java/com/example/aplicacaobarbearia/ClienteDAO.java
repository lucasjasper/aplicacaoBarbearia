package com.example.aplicacaobarbearia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private ConexaoBD conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context) {

        conexao = new ConexaoBD(context);
        banco = conexao.getWritableDatabase();

    }

    public void inserir(Cliente cliente) {

        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());

        banco.insert("cliente", null, values);

    }

    public List<Cliente> consultaTodos() {

        List<Cliente> clientes = new ArrayList<>();

        Cursor cursor = banco.query("cliente", new String[] {"id", "nome", "telefone"},
                null, null, null, null, null);

        if (cursor.getCount() > 0) {

            while(cursor.moveToNext()) {

                Cliente cliente = new Cliente();
                cliente.setId(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setTelefone(cursor.getString(2));

                clientes.add(cliente);

            }

            cursor.close();

        }

        return clientes;

    }

    public void atualizarCliente(Cliente c) {

        ContentValues values = new ContentValues();

        values.put("nome", c.getNome());
        values.put("telefone", c.getTelefone());

        banco.update("cliente", values, "id = ?", new String[] {c.getId().toString()});

    }

    public void excluirCliente(Cliente c) {

        banco.delete("cliente", "id = ?", new String[] {c.getId().toString()});

    }

}
