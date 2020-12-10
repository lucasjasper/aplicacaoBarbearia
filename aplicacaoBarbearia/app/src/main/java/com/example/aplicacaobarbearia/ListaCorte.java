package com.example.aplicacaobarbearia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class ListaCorte extends AppCompatActivity {

    private ListView listView;
    private CorteDAO dao;
    private List<Corte> cortes;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_corte);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.listViewCortes);

        dao = new CorteDAO(this);
        cortes = dao.consultaTodos();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        if (!cortes.isEmpty()) {

            listarCortes();

        }

        registerForContextMenu(listView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Corte novoCorte = new Corte();

                novoCorte.setDescricao("Corte Padrão");
                novoCorte.setValor("20,00");

                dao.inserir(novoCorte);

                listarCortes();

                Mensagem("Novo corte inserido.");

            }

        });

    }

    public void listarCortes(){

        cortes.clear();
        cortes = dao.consultaTodos();

        adapter.clear();

        for (Corte c : cortes) {

            StringBuilder corte = new StringBuilder();

            corte.append("Corte: ").append(c.getId()).append("-").append(c.getDescricao()).append("\n");
            corte.append("Valor: R$").append(c.getValor());

            adapter.add(corte.toString());

        }

        adapter.notifyDataSetChanged();

    }

    public void onResume() {
        super.onResume();

        listarCortes();

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_corte, menu);

    }

    public void excluir(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Corte deleteCorte = cortes.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o corte?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cortes.remove(deleteCorte);
                        dao.excluirCorte(deleteCorte);
                        listarCortes();

                    }

                }).create();

        dialog.show();

    }

    public void atualizar(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Corte atualizaCorte = cortes.get(menuInfo.position);

        Intent it = new Intent(this, EditaCorte.class);
        it.putExtra("corte", atualizaCorte);
        startActivity(it);

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}