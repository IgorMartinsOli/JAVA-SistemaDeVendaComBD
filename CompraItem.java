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
public class CompraItem {
    private int pk_item;
    private int fk_compra;
    private Produto produto;
    private int qtd;
    private double valor_unitario;

    public CompraItem(int pk_item, int fk_compra, Produto produto, int qtd, double valor_unitario) {
        this.pk_item = pk_item;
        this.fk_compra = fk_compra;
        this.produto = produto;
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }

    public CompraItem(int fk_compra, Produto produto, int qtd, double valor_unitario) {
        this.fk_compra = fk_compra;
        this.produto = produto;
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }

    public CompraItem(Produto produto, int qtd, double valor_unitario) {
        this.produto = produto;
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }
    
    

    public int getPk_item() {
        return pk_item;
    }

    public void setPk_item(int pk_item) {
        this.pk_item = pk_item;
    }

    public int getFk_compra() {
        return fk_compra;
    }

    public void setFk_compra(int fk_compra) {
        this.fk_compra = fk_compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    @Override
    public String toString() {
        return "CompraItem{" + "pk_item=" + pk_item + ", fk_compra=" + fk_compra + ", produto=" + produto + ", qtd=" + qtd + ", valor_unitario=" + valor_unitario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.pk_item;
        hash = 53 * hash + this.fk_compra;
        hash = 53 * hash + Objects.hashCode(this.produto);
        hash = 53 * hash + this.qtd;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valor_unitario) ^ (Double.doubleToLongBits(this.valor_unitario) >>> 32));
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
        final CompraItem other = (CompraItem) obj;
        if (this.pk_item != other.pk_item) {
            return false;
        }
        if (this.fk_compra != other.fk_compra) {
            return false;
        }
        if (this.qtd != other.qtd) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor_unitario) != Double.doubleToLongBits(other.valor_unitario)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }
}
