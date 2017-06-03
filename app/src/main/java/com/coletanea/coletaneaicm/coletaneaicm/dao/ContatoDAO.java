package com.coletanea.coletaneaicm.coletaneaicm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.coletanea.coletaneaicm.coletaneaicm.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ana Maria on 31/05/2017.
 */

public class ContatoDAO extends SQLiteOpenHelper {

    public ContatoDAO(Context context) {
        super(context, "agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contatos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS contatos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesContato(aluno);

        db.insert("contatos", null, dados);
    }

    @NonNull
    private ContentValues getContentValuesContato(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("email", aluno.getEmail());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Aluno> getAlunos() {

        String sql = "SELECT * FROM contatos";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deleta(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();

        long id = (aluno.getId());
        String[] params = {String.valueOf(id)};

        db.delete("contatos", "id = ?", params);

    }

    public void altera(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesContato(aluno);

        long id = aluno.getId();
        String[] params = {String.valueOf(id)};

        db.update("contatos", dados, "id = ?", params);
    }
}
