package com.example.aplicacaobarbearia;

import java.io.Serializable;

public class Servico implements Serializable {

    private Integer id;
    private String data_corte;
    private String corte;
    private String cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData_corte() {
        return data_corte;
    }

    public void setData_corte(String data_corte) {
        this.data_corte = data_corte;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
