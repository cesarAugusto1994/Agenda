package com.coletanea.coletaneaicm.coletaneaicm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.coletanea.coletaneaicm.coletaneaicm.dao.ContatoDAO;
import com.coletanea.coletaneaicm.coletaneaicm.modelo.Aluno;

import java.util.List;

public class ListaContatosActivity extends AppCompatActivity {

    private ListView lista_alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        Button novoContato = (Button) findViewById(R.id.novo_contato);

        lista_alunos = (ListView) findViewById(R.id.lista_alunos);

        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
                startActivity(irParaFormulario);
            }
        });

        registerForContextMenu(lista_alunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Aluno aluno = (Aluno) lista_alunos.getItemAtPosition(info.position);

                ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
                dao.deleta(aluno);
                dao.close();
                loadList();

                Toast.makeText(ListaContatosActivity.this, aluno.getNome(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    private void loadList() {
        ContatoDAO dao = new ContatoDAO(this);
        List<Aluno> contatos = dao.getAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, contatos);
        lista_alunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }
}
