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

public class ListaServico extends AppCompatActivity {

    private ListView listView;
    private ServicoDAO dao;
    private List<Servico> servicos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servico);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.listViewServicos);

        dao = new ServicoDAO(this);
        servicos = dao.consultaTodos();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        if (!servicos.isEmpty()) {

            listarServicos();

        }

        registerForContextMenu(listView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Servico novoServico = new Servico();

                novoServico.setData_corte("Segunda-feira - 10:00");
                novoServico.setCorte("Tipo corte");
                novoServico.setCliente("Nome cliente");

                dao.inserir(novoServico);

                listarServicos();

                Mensagem("Adicionado novo serviço.");

            }

        });

    }

    public void listarServicos(){

        servicos.clear();
        servicos = dao.consultaTodos();

        adapter.clear();

        for (Servico s : servicos) {

            StringBuilder servico = new StringBuilder();

            servico.append("Horário: ").append(s.getData_corte()).append("\n");
            servico.append("Corte: ").append(s.getCorte()).append("\n");
            servico.append("Cliente: ").append(s.getCliente());

            adapter.add(servico.toString());

        }

        adapter.notifyDataSetChanged();

    }

    public void onResume() {
        super.onResume();

        listarServicos();

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_servico, menu);

    }

    public void excluir(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Servico deleteServico = servicos.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja finalizar esse serviço?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        servicos.remove(deleteServico);
                        dao.excluirServico(deleteServico);
                        listarServicos();

                    }

                }).create();

        dialog.show();

    }

    public void atualizar(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Servico atualizarServico = servicos.get(menuInfo.position);

        Intent it = new Intent(this, EditaServico.class);
        it.putExtra("agenda", atualizarServico);
        startActivity(it);

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}