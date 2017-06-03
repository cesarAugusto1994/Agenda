package com.coletanea.coletaneaicm.coletaneaicm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coletanea.coletaneaicm.coletaneaicm.dao.ContatoDAO;
import com.coletanea.coletaneaicm.coletaneaicm.modelo.Aluno;

import java.util.zip.Inflater;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("contato");

        if (aluno != null) {
            helper.setContato(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok :
                Aluno aluno = helper.getContato();
                ContatoDAO dao = new ContatoDAO(this);

                if(Integer.getInteger(String.valueOf(aluno.getId())) != null){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }

                dao.close();

                Toast.makeText(FormularioActivity.this, "Salvo", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
