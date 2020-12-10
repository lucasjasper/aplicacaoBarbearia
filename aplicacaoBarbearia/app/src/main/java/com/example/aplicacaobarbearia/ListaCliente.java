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

public class ListaCliente extends AppCompatActivity {

    private ListView listView;
    private ClienteDAO dao;
    private List<Cliente> clientes;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.listViewClientes);

        dao = new ClienteDAO(this);
        clientes = dao.consultaTodos();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        if (!clientes.isEmpty()) {

            listarClientes();

        }

        registerForContextMenu(listView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cliente novoCliente = new Cliente();

                novoCliente.setNome("Novo Cliente");
                novoCliente.setTelefone("99999999");

                dao.inserir(novoCliente);

                listarClientes();

                Mensagem("Novo corte inserido.");

            }

        });

    }

    public void listarClientes(){

        clientes.clear();
        clientes = dao.consultaTodos();

        adapter.clear();

        for (Cliente c : clientes) {

            StringBuilder cliente = new StringBuilder();

            cliente.append("Cliente: ").append(c.getId()).append("-").append(c.getNome()).append("\n");
            cliente.append("Telefone: ").append(c.getTelefone());

            adapter.add(cliente.toString());

        }

        adapter.notifyDataSetChanged();

    }

    public void onResume() {
        super.onResume();

        listarClientes();

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_cliente, menu);

    }

    public void excluir(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cliente deleteCliente = clientes.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir esse cliente?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        clientes.remove(deleteCliente);
                        dao.excluirCliente(deleteCliente);
                        listarClientes();

                    }

                }).create();

        dialog.show();

    }

    public void atualizar(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cliente atualizarCliente = clientes.get(menuInfo.position);

        Intent it = new Intent(this, EditaCliente.class);
        it.putExtra("cliente", atualizarCliente);
        startActivity(it);

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

}