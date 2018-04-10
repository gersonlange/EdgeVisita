package br.inf.edge.suporte.visita.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dados
{
    private String json;

    public Dados(String json) throws  JSONException {
        this.json = json;

        JSONObject responseAsJSON = new JSONObject(json);

        JSONArray regioesJon = responseAsJSON.getJSONArray("regioes");
        List<Regiao> regioes = new ArrayList<>();

        for ( int i = 0 ; i < regioesJon.length() ; i++ ) {
            JSONObject regiaoJson = (JSONObject)regioesJon.get(i);

            Regiao regiao = new Regiao();
            regiao.setCodigo(regiaoJson.getInt("codigo"));
            regiao.setRegiao(regiaoJson.getString("nome"));

            if ( regiaoJson.has("data") )
                regiao.setData(regiaoJson.getString("data"));

            if ( regiaoJson.has("observacao") )
                regiao.setObservacao(regiaoJson.getString("observacao"));

            JSONArray clientesJson = regiaoJson.getJSONArray("clientes");
            List<Cliente> clientes = new ArrayList<>();

            for ( int a = 0 ; a < clientesJson.length() ; a++ ) {
                JSONObject clienteJson = (JSONObject)clientesJson.get(a);

                Cliente cliente =  new Cliente();
                cliente.setCodigo(clienteJson.getInt("codigo"));
                cliente.setNome(clienteJson.getString("nome"));

                if ( clienteJson.has("observacao") )
                    cliente.setObservacao(clienteJson.getString("observacao"));

                clientes.add(cliente);
            }

            Clientes vclientes = new Clientes();
            vclientes.setClientes(clientes);

            regiao.setClientes(vclientes);

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

    public String getJson() {
        return json;
    }
}