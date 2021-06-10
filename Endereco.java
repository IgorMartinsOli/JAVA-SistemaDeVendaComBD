/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.controller;

import java.util.Objects;

/**
 *
 * @author igor_
 */
public class Endereco {
    
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    
    private int pk;
    private int fk;
    
    private boolean persistido = false;

    public Endereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep, int pk, int fk) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.pk = pk;
        this.fk = fk;
        persistido = true;
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        persistido = true;
    }

    public boolean isPersistido() {
        return persistido;
    }

    public void setPersistido(boolean persistido) {
        this.persistido = persistido;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        persistido = false;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        persistido = false;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
        persistido = false;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
        persistido = false;
    }

    public String getPais() {
        return pais;        
    }

    public void setPais(String pais) {
        this.pais = pais;
        persistido = false;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
        persistido = false;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
        persistido = false;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
        persistido = false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.logradouro);
        hash = 37 * hash + Objects.hashCode(this.bairro);
        hash = 37 * hash + Objects.hashCode(this.cidade);
        hash = 37 * hash + Objects.hashCode(this.pais);
        hash = 37 * hash + Objects.hashCode(this.cep);
        hash = 37 * hash + this.pk;
        hash = 37 * hash + this.fk;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.fk != other.fk) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", pais=" + pais + ", cep=" + cep + ", pk=" + pk + ", fk=" + fk + '}';
    }
    
    public void print(){
        System.out.println(this);
    }
    
    
}
