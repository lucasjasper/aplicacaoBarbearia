package com.example.aplicacaobarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastraUsuario extends AppCompatActivity {

    EditText editTextCpf, editTextEmail, editTextCadUsuario,
             editTextCadNome, editTextCadSenha, editTextCadSenhaRepeticao;

    String cpf, email, usuario, nome, senha, confirmaSenha;

    Button cadastrar, cancelar;

    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        editTextCpf = findViewById(R.id.editTextCpf);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCadUsuario = findViewById(R.id.editTextCadUsuario);
        editTextCadNome = findViewById(R.id.editTextCadNome);
        editTextCadSenha = findViewById(R.id.editTextCadSenha);
        editTextCadSenhaRepeticao = findViewById(R.id.editTextCadSenhaRepeticao);

        cancelar = (Button) findViewById(R.id.buttonCancelarCadastro);
        cadastrar = findViewById(R.id.buttonConfirmarCadastro);

        dao = new UsuarioDAO(this);

        cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cadastrarUsuario();

            }

        });

    }

    public void Mensagem(String txt){

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

    public void cadastrarUsuario() {

        cpf = editTextCpf.getText().toString();
        email = editTextEmail.getText().toString();
        usuario = editTextCadUsuario.getText().toString();
        nome = editTextCadNome.getText().toString();
        senha = editTextCadSenha.getText().toString();
        confirmaSenha = editTextCadSenhaRepeticao.getText().toString();

        if (!cpf.equals("") &&
            !usuario.equals("") &&
            !nome.equals("") &&
            !senha.equals("") &&
            !confirmaSenha.equals("") && (senha.equals(confirmaSenha))) {

            Usuario novoUsuario = new Usuario();

            novoUsuario.setCpf(cpf);
            novoUsuario.setEmail(email);
            novoUsuario.setUsuario(usuario);
            novoUsuario.setNome(nome);
            novoUsuario.setSenha(senha);

            dao.inserir(novoUsuario);

            Mensagem("Usuário cadastrado com sucesso!");
            limparCampos();

            Intent it = new Intent(CadastraUsuario.this, Login.class);
            startActivity(it);

        } else if (!senha.equals(confirmaSenha)) {

            Mensagem("Senha não confirmada.");

        } else {

            Mensagem("Verifique os dados.");

        }

    }

    public void limparCampos() {

        editTextCpf.setText("");
        editTextEmail.setText("");
        editTextCadUsuario.setText("");
        editTextCadNome.setText("");
        editTextCadSenha.setText("");
        editTextCadSenhaRepeticao.setText("");

    }

    public void botaoCancelar (View view){

        Intent it = new Intent(CadastraUsuario.this, Login.class);
        startActivity(it);

    }



}