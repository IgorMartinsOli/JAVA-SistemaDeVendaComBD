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
public class Produto {
    private int pkProduto;
    private String nome;
    private int estoqueMinimo;
    private int qtdEstoque;

    public Produto(int pkProduto, String nome, int estoqueMinimo, int qtdEstoque) {
        this.pkProduto = pkProduto;
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(String nome, int estoqueMinimo, int qtdEstoque) {
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
    }

    public int getPkProduto() {
        return pkProduto;
    }

    public void setPkProduto(int pkProduto) {
        this.pkProduto = pkProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" + "pkProduto=" + pkProduto + ", nome=" + nome + ", estoqueMinimo=" + estoqueMinimo + ", qtdEstoque=" + qtdEstoque + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.pkProduto;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + this.estoqueMinimo;
        hash = 83 * hash + this.qtdEstoque;
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
        final Produto other = (Produto) obj;
        if (this.pkProduto != other.pkProduto) {
            return false;
        }
        if (this.estoqueMinimo != other.estoqueMinimo) {
            return false;
        }
        if (this.qtdEstoque != other.qtdEstoque) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    public void print(){
        System.out.println(this);
    }
    
    
}
