package com.example.aplicacaobarbearia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {

    private static final String name = "banco2";
    private static final int version = 1;

    public ConexaoBD(Context context) {

        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuario (                          " +
                   "id       integer     primary key autoincrement, " +
                   "cpf      varchar(50) not null,                  " +
                   "email    varchar(50),                           " +
                   "usuario  varchar(50) not null,                  " +
                   "nome     varchar(50) not null,                  " +
                   "senha    varchar(50) not null);                 ");

        db.execSQL("create table corte (                              " +
                    "id        integer     primary key autoincrement, " +
                    "descricao varchar(50) not null,                  " +
                    "valor     varchar(50) not null);                 ");

        db.execSQL("create table cliente (                           " +
                   "id        integer     primary key autoincrement, " +
                   "nome      varchar(50) not null,                  " +
                   "telefone  varchar(50) not null);                 ");

        db.execSQL("create table agenda (                             " +
                   "id         integer     primary key autoincrement, " +
                   "data_corte varchar(20) not null,                  " +
                   "corte      varchar(50) not null,                  " +
                   "cliente    varchar(50) not null);                 ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
