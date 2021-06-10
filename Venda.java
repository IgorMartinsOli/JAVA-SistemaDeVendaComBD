/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author igor_
 */
public class Venda {
    private int pk_venda;
    private Cliente fk_cliente;
    private Fornecedor fk_fornecedor;
    private int numero;
    private Date data;
    private ArrayList<VendaItem> itens;

    public Venda(int pk_venda, Cliente fk_cliente, Fornecedor fk_fornecedor, int numero, Date data, ArrayList<VendaItem> itens) {
        this.pk_venda = pk_venda;
        this.fk_cliente = fk_cliente;
        this.fk_fornecedor = fk_fornecedor;
        this.numero = numero;
        this.data = data;
        this.itens = itens;
    }

    public Venda(Cliente fk_cliente, Fornecedor fk_fornecedor, int numero, Date data, ArrayList<VendaItem> itens) {
        this.fk_cliente = fk_cliente;
        this.fk_fornecedor = fk_fornecedor;
        this.numero = numero;
        this.data = data;
        this.itens = itens;
    }

    public int getPk_venda() {
        return pk_venda;
    }

    public void setPk_venda(int pk_venda) {
        this.pk_venda = pk_venda;
    }

    public Cliente getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(Cliente fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public Fornecedor getFk_fornecedor() {
        return fk_fornecedor;
    }

    public void setFk_fornecedor(Fornecedor fk_fornecedor) {
        this.fk_fornecedor = fk_fornecedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<VendaItem> getItens() {
        return itens;
    }

    public void setItens(ArrayList<VendaItem> itens) {
        this.itens = itens;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.pk_venda;
        hash = 29 * hash + Objects.hashCode(this.fk_cliente);
        hash = 29 * hash + Objects.hashCode(this.fk_fornecedor);
        hash = 29 * hash + this.numero;
        hash = 29 * hash + Objects.hashCode(this.data);
        hash = 29 * hash + Objects.hashCode(this.itens);
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
        final Venda other = (Venda) obj;
        if (this.pk_venda != other.pk_venda) {
            return false;
        }
        return this.numero == other.numero;
    }

    @Override
    public String toString() {
        return "Venda{" + "pk_venda=" + pk_venda + ", fk_cliente=" + fk_cliente + ", fk_fornecedor=" + fk_fornecedor + ", numero=" + numero + ", data=" + data + ", itens=" + itens + '}';
    }
    
}
