package br.inf.edge.suporte.visita.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Cliente {
    private int codigo;
    private String nome;
    private String observacao;
    private Date dataInicio, dataTermino;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public JSONObject getJson() {
        try {
            JSONObject json = new JSONObject();

            json.put("codigo", codigo);
            json.put("nome", nome);

            if ( observacao != null )
                json.put("observacao", observacao);

            return json;
        }
        catch (JSONException exc) {

        }

        return null;
    }
}
