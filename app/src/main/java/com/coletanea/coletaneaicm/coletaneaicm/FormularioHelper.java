package com.coletanea.coletaneaicm.coletaneaicm;

import android.widget.EditText;
import android.widget.RatingBar;

import com.coletanea.coletaneaicm.coletaneaicm.modelo.Aluno;

/**
 * Created by Ana Maria on 31/05/2017.
 */

public class FormularioHelper {

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText email;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {

        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
    }

    public Aluno getContato() {

        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }

}
