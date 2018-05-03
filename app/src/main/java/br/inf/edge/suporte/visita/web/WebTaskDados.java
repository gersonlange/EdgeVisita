package br.inf.edge.suporte.visita.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.model.Dados;

public class WebTaskDados extends WebTaskBase{

    private static final String SERVICE_NAME = "dados";

    private String token;

    public WebTaskDados(Context context, String token) {
        super(context, SERVICE_NAME);
        this.token = token;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("token", token);

        JSONObject json = new JSONObject(requestMap);

        return  json.toString();
    }

    @Override
    public void handleResponse(String response) {
        try {
            Dados dados = new Dados(response);

            EventBus.getDefault().post(dados);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.label_error_invalid_response)));
        }
    }
}
