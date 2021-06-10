/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author igor_
 */
public class Pessoa {
    
    private int pk;
    String nome;
    
    ArrayList<Endereco> enderecos;

    public Pessoa(int pk, String nome) {
        this.pk = pk;
        this.nome = nome;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public ArrayList<Endereco> getEnderecos() {
        if (this.enderecos == null){
            this.enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.pk;
        hash = 71 * hash + Objects.hashCode(this.nome);
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
        final Pessoa other = (Pessoa) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "pk=" + pk + ", nome=" + nome + '}';
    }
    
    public void print(){
        System.out.println(this);
        
        for (Endereco e: enderecos){
            e.print();
        }
    }
    
    
}
