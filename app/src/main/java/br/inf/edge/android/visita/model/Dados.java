package br.inf.edge.android.visita.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dados
{
    public Dados(String response) throws  JSONException {
        JSONObject responseAsJSON = new JSONObject(response);

        JSONArray regioesJon = responseAsJSON.getJSONArray("regioes");
        List<Regiao> regioes = new ArrayList<>();

        for ( int i = 0 ; i < regioesJon.length() ; i++ ) {
            JSONObject regiaoJson = (JSONObject)regioesJon.get(i);

            Regiao regiao = new Regiao();
            regiao.setCodigo(regiaoJson.getInt("codigo"));
            regiao.setRegiao(regiaoJson.getString("nome"));

            if ( regiaoJson.has("observacao") )
                regiao.setObservacao(regiaoJson.getString("observacao"));

            JSONArray clientesJson = regiaoJson.getJSONArray("clientes");
            List<Cliente> clientes = new ArrayList<>();

            for ( int a = 0 ; a < regioesJon.length() ; a++ ) {
                JSONObject clienteJson = (JSONObject)regioesJon.get(a);

                Cliente cliente =  new Cliente();
                cliente.setCodigo(clienteJson.getInt("codigo"));
                cliente.setNome(clienteJson.getString("nome"));

                clientes.add(cliente);
            }

            regiao.setClientes(clientes);

            regioes.add(regiao);
        }

        setRegiao(regioes);
    }

    private List<Regiao> regiao;

    public List<Regiao> getRegiao() {
        return regiao;
    }

    public void setRegiao(List<Regiao> regiao) {
        this.regiao = regiao;
    }
}