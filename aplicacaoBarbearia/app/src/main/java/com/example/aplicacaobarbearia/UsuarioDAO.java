package com.example.aplicacaobarbearia;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private ConexaoBD conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context) {

        conexao = new ConexaoBD(context);
        banco = conexao.getWritableDatabase();

    }

    public void inserir(Usuario usuario) {

        ContentValues values = new ContentValues();

        values.put("cpf", usuario.getCpf());
        values.put("email", usuario.getEmail());
        values.put("usuario", usuario.getUsuario());
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());

        banco.insert("usuario", null, values);

    }

    public List<Usuario> consultaTodos() {

        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = banco.query("usuario", new String[] {"id", "cpf", "email", "usuario", "nome", "senha"},
                                    null, null, null, null, null);

        if (cursor.getCount() > 0) {

            while(cursor.moveToNext()) {

                Usuario user = new Usuario();
                user.setId(cursor.getInt(0));
                user.setCpf(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setUsuario(cursor.getString(3));
                user.setNome(cursor.getString(4));
                user.setSenha(cursor.getString(5));

                usuarios.add(user);

            }

            cursor.close();

        }

        return usuarios;

    }

    public int buscaIdUsuario (String usuario, String senha) {

        List<Usuario> usuarios = consultaTodos();

        int validaId = 0;

        for(Usuario user : usuarios) {

            if(user.getUsuario().equals(usuario) && user.getSenha().equals(senha)) {

                validaId = user.getId();

            }

        }

        return validaId;

    }

}
