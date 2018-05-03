package br.inf.edge.suporte.visita.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.inf.edge.suporte.visita.data.Database;

public class VisitaDAO {

    public void insert(
            int codigoCliente,
            int codigoRegiao,
            String data,
            String hora
    ) {
        SQLiteDatabase db = Database.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("codigo_cliente", codigoCliente);
        values.put("codigo_regiao" , codigoRegiao);
        values.put("data"          , data);
        values.put("hora_inicio"   , hora);

        db.insert("visita", null, values);
    }

    public void fecha (
            int codigoCliente,
            String checklist,
            String chamada,
            String texto,
            String hora
    ) {
        SQLiteDatabase db = Database.get().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("hora_final", hora);
        values.put("checklist" , checklist);
        values.put("chamada"   , chamada);
        values.put("texto"     , texto);

        db.update("visita", values, "hora_final IS null AND codigo_cliente = ?",
                new String[] { String.valueOf(codigoCliente) });
    }
}
