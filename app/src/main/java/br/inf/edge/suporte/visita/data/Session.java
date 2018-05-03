package br.inf.edge.suporte.visita.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private final String CATEGORY_SESSION = "session";

    public static final String USUARIO      = "usuario";
    public static final String USUARIO_NOME = "usuario_nome";
    public static final String USUARIO_TOKEN = "usuario_token";

    private SharedPreferences sharedPreferences;

    public Session(Context context){
        sharedPreferences =
                context.getSharedPreferences(CATEGORY_SESSION,
                        Context.MODE_PRIVATE);
    }


    public Session set(String key, String value){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

        return this;
    }

    public String get(String key){
        return sharedPreferences.getString(key,"");
    }
}
