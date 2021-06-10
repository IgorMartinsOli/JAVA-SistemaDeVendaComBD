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
public class Cargo {
    
    
    private String descricao;
    private String nome;
    
    private int pkCargo;

    public Cargo(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public Cargo(String descricao, String nome, int pkCargo) {
        this.descricao = descricao;
        this.nome = nome;
        this.pkCargo = pkCargo;
    }
    
    public void print(){
        System.out.println(this);
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPkCargo() {
        return pkCargo;
    }

    public void setPkCargo(int pkCargo) {
        this.pkCargo = pkCargo;
    }

    @Override
    public String toString() {
        return "Cargo{" + "descricao=" + descricao + ", nome=" + nome + ", pkCargo=" + pkCargo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + this.pkCargo;
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
        final Cargo other = (Cargo) obj;
        if (this.pkCargo != other.pkCargo) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }    
}