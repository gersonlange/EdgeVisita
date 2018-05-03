package br.inf.edge.suporte.visita.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.inf.edge.android.visita.model.Regiao;
import br.inf.edge.suporte.visita.data.Database;
import br.inf.edge.suporte.visita.model.Cliente;

public class DadosDAO {

    public void insertRegiao(List<Regiao> regioes) {

        SQLiteDatabase db = Database.get().getWritableDatabase();

        for ( Regiao regiao : regioes ) {
            ContentValues values = new ContentValues();
            values.put("codigo", regiao.getCodigo());
            values.put("nome"  , regiao.getRegiao());
            values.put("data"  , regiao.getData());
            values.put("observacao", regiao.getObservacao());

            db.insert("regioes", null, values);
        }

        db.close();
    }

    public void insertCliente(List<Cliente> clientes) {

        SQLiteDatabase db = Database.get().getWritableDatabase();

        for ( Cliente cliente : clientes ) {
            ContentValues values = new ContentValues();
            values.put("codigo", cliente.getCodigo());
            values.put("nome"  , cliente.getNome());
            values.put("endereco"  , cliente.getEndereco());
            values.put("observacao", cliente.getObservacao());
            values.put("codigo_regiao", cliente.getCodigoRegiao());

            db.insert("clientes", null, values);
        }

        db.close();
    }

    public List<Regiao> getRegioes() {
        List<Regiao> regiaoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM regioes";
        SQLiteDatabase db = Database.get().getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Regiao regiao = new Regiao();
                regiao.setCodigo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("codigo"))));
                regiao.setRegiao(cursor.getString(cursor.getColumnIndex("nome")));
                regiao.setObservacao(cursor.getString(cursor.getColumnIndex("observacao")));
                regiao.setData(cursor.getString(cursor.getColumnIndex("data")));

                regiaoList.add(regiao);
            } while (cursor.moveToNext());
        }

        return regiaoList;
    }

    public List<Cliente> getClientes( int codigoRegiao ) {
        List<Cliente> clientesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM clientes WHERE codigo_regiao = " + codigoRegiao;
        SQLiteDatabase db = Database.get().getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("codigo"))));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                cliente.setObservacao(cursor.getString(cursor.getColumnIndex("observacao")));
                cliente.setCodigoRegiao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("codigo_regiao"))));

                clientesList.add(cliente);
            } while (cursor.moveToNext());
        }

        return clientesList;
    }

    public Cliente getCliente( int codigo ) {
        Cliente cliente = new Cliente();

        String selectQuery = "SELECT * FROM clientes WHERE codigo = " + codigo;
        SQLiteDatabase db = Database.get().getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                cliente.setCodigo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("codigo"))));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                cliente.setObservacao(cursor.getString(cursor.getColumnIndex("observacao")));
                cliente.setCodigoRegiao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("codigo_regiao"))));
            } while (cursor.moveToNext());
        }

        return cliente;
    }
}
