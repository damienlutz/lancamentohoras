package com.example.damien.lancamentodehoras.models;

/**
 * Created by FranciscoHenrique on 17/07/2015.
 */
public class lancamentos {
    private String horasLancamentos;
    private String projetoLancamento;
    private String emailLancamento;
    private long _id;

    public String getEmailLancamento() {
        return emailLancamento;
    }

    public void setEmailLancamento(String emailLancamento) {
        this.emailLancamento = emailLancamento;
    }

    public String getProjetoLancamento() {
        return projetoLancamento;
    }

    public void setProjetoLancamento(String projetoLancamento) {
        this.projetoLancamento = projetoLancamento;
    }

    public String getHorasLancamentos() {
        return horasLancamentos;
    }

    public void setHorasLancamentos(String horasLancamentos) {
        this.horasLancamentos = horasLancamentos;
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }
}