package br.inf.edge.suporte.visita.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static Database instance;
    private static Context context;

    private static final String DB_NAME = "visitas.db";
    private static final int DB_VERSION = 1;

    public static void init(Context context) {
        Database.context = context;

        if ( instance != null )
            instance.close();

        instance = new Database(context);
    }

    private Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         * Tabela Regioes
         */
        sqLiteDatabase.execSQL(
                "CREATE TABLE regioes (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                      "codigo INTEGER," +
                                      "nome TEXT," +
                                      "data DATE," +
                                      "observacao TEXT)");

        /*
         * Tabela Clientes
         */
        sqLiteDatabase.execSQL(
                "CREATE TABLE clientes (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                       "codigo INTEGER," +
                                       "nome TEXT," +
                                       "endereco TEXT," +
                                       "observacao TEXT," +
                                       "codigo_regiao INTEGER)");

        /*
         * Tabela Chamadas
         */
        sqLiteDatabase.execSQL(
                "CREATE TABLE chamadas (id INTEGER," +
                                       "item TEXT)");

        /*
         * Tabela Checklist
         */
        sqLiteDatabase.execSQL(
                "CREATE TABLE checklist (id INTEGER," +
                                        "item TEXT)");

        /*
         * Tabela Visita
         */
        sqLiteDatabase.execSQL(
                "CREATE TABLE visita (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                     "codigo_cliente INTEGER," +
                                     "codigo_regiao INTEGER," +
                                     "data TEXT," +
                                     "hora_inicio TEXT," +
                                     "hora_final TEXT," +
                                     "checklist TEXT," +
                                     "chamada TEXT," +
                                     "texto TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void drop() {
        instance.close();
        instance = null;

        context.deleteDatabase(DB_NAME);

        init(context);
    }

    public static Database get() {
        return instance;
    }
}
