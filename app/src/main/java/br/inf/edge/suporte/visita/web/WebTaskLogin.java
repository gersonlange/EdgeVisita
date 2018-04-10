package br.inf.edge.suporte.visita.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.model.Usuario;

public class WebTaskLogin extends WebTaskBase{

    private static final String SERVICE_NAME = "login";

    private String usuario, senha;

    public WebTaskLogin(Context context, String usuario, String password) {
        super(context, SERVICE_NAME);
        this.usuario = usuario;
        this.senha = password;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("username", usuario);
        requestMap.put("password", senha);

        JSONObject json = new JSONObject(requestMap);

        return  json.toString();
    }

    @Override
    public void handleResponse(String response) {
        Usuario user = new Usuario();
        try {
            JSONObject responseAsJSON = new JSONObject(response);
            user.setNome(responseAsJSON.getString("name"));
            user.setToken(responseAsJSON.getString("token"));
            user.setUsuario(usuario);

            EventBus.getDefault().post(user);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.label_error_invalid_response)));
        }
    }


}
