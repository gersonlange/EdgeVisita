package br.inf.edge.suporte.visita.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.inf.edge.android.visita.model.Regiao;

public class Dados
{
    private List<Regiao> regioes;
    private List<Cliente> clientes;

    public Dados(String json) throws JSONException {

        regioes = new ArrayList<>();
        clientes = new ArrayList<>();

        JSONObject responseAsJSON = new JSONObject(json);
        JSONArray regioesJon = responseAsJSON.getJSONArray("regioes");

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

            for ( int a = 0 ; a < clientesJson.length() ; a++ ) {
                JSONObject clienteJson = (JSONObject)clientesJson.get(a);

                Cliente cliente =  new Cliente()
                    .setCodigo(clienteJson.getInt("codigo"))
                    .setNome(clienteJson.getString("nome"))
                    .setEndereco(clienteJson.getString("endereco"))
                    .setCodigoRegiao(regiao.getCodigo());

                if ( clienteJson.has("observacao") )
                    cliente.setObservacao(clienteJson.getString("observacao"));

                clientes.add(cliente);
            }

            clientes = clientes;
            regioes.add(regiao);
        }
    }

    public List<Regiao> getRegioes() {
        return regioes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}