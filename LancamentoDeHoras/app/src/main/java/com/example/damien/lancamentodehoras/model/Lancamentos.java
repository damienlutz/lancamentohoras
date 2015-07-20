package com.example.damien.lancamentodehoras.model;

import java.io.Serializable;

/**
 * Created by FranciscoHenrique on 17/07/2015.
 */
public class Lancamentos implements Serializable {

    private Integer _id;
    private String usuarioLancamento;
    private String horasLancamentos;
    private String projetoLancamento;
    private String emailLancamento;

    public Lancamentos(String usuarioLancamento, String horasLancamentos) {
        this._id = _id;
        this.usuarioLancamento = usuarioLancamento;
        this.horasLancamentos = horasLancamentos;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsuarioLancamento() {
        return usuarioLancamento;
    }

    public void setUsuarioLancamento(String usuarioLancamento) {
        this.usuarioLancamento = usuarioLancamento;
    }

    public String getHorasLancamentos() {
        return horasLancamentos;
    }

    public void setHorasLancamentos(String horasLancamentos) {
        this.horasLancamentos = horasLancamentos;
    }

    public String getProjetoLancamento() {
        return projetoLancamento;
    }

    public void setProjetoLancamento(String projetoLancamento) {
        this.projetoLancamento = projetoLancamento;
    }

    public String getEmailLancamento() {
        return emailLancamento;
    }

    public void setEmailLancamento(String emailLancamento) {
        this.emailLancamento = emailLancamento;
    }
}