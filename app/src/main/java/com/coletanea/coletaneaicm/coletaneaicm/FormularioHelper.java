package com.coletanea.coletaneaicm.coletaneaicm;

import android.widget.EditText;
import android.widget.RatingBar;

import com.coletanea.coletaneaicm.coletaneaicm.modelo.Aluno;

/**
 * Created by Ana Maria on 31/05/2017.
 */

public class FormularioHelper {

    private final EditText nome;
    private final EditText endereco;
    private final EditText telefone;
    private final EditText email;
    private final RatingBar nota;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {

        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        aluno = new Aluno();
    }

    public Aluno getContato() {

        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }

    public void setContato(Aluno aluno) {

        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        nota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }

}
