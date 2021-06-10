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
public class VendaItem {
    private int pk_item;
    private int fk_venda;
    private Produto fk_produto;
    private int qtd;
    private double valor_unitario;

    public VendaItem(int pk_item, int fk_venda, Produto fk_produto, int qtd, double valor_unitario) {
        this.pk_item = pk_item;
        this.fk_venda = fk_venda;
        this.fk_produto = fk_produto;
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }

    public VendaItem(int fk_venda, Produto fk_produto, int qtd, double valor_unitario) {
        this.fk_venda = fk_venda;
        this.fk_produto = fk_produto;
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }

    public int getPk_item() {
        return pk_item;
    }

    public void setPk_item(int pk_item) {
        this.pk_item = pk_item;
    }

    public int getFk_venda() {
        return fk_venda;
    }

    public void setFk_venda(int fk_venda) {
        this.fk_venda = fk_venda;
    }

    public Produto getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(Produto fk_produto) {
        this.fk_produto = fk_produto;
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
        return "VendaItem{" + "pk_item=" + pk_item + ", fk_venda=" + fk_venda + ", fk_produto=" + fk_produto + ", qtd=" + qtd + ", valor_unitario=" + valor_unitario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.pk_item;
        hash = 53 * hash + this.fk_venda;
        hash = 53 * hash + Objects.hashCode(this.fk_produto);
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
        final VendaItem other = (VendaItem) obj;
        if (this.pk_item != other.pk_item) {
            return false;
        }
        return this.fk_venda == other.fk_venda;
    }
    
}
