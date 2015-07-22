package com.example.damien.lancamentodehoras.model;

import java.io.Serializable;

/**
 * Created by FranciscoHenrique on 22/07/2015.
 */
public class Usuarios implements Serializable {

    private String usuarioUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;

    private Integer _id;

    public Usuarios(String usuarioUsuario, String senhaUsuario) {
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(String usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

}
