package br.inf.edge.suporte.visita.model;

/**
 * Created by marceloquinta on 03/03/2018.
 */

public class Usuario {

    private String usuario, nome, token;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
