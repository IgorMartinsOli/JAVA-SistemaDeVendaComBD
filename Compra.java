/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.controller;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author igor_
 */
public class Compra {
    
    private int pk;
    private int numero;
    private Date data;
    private Fornecedor fornecedor; //private int fk_fornecedor (ERRADO!!!)   
    private ArrayList<CompraItem> itens;

    public Compra(int pk, int numero, Date data, Fornecedor fornecedor, ArrayList<CompraItem> itens) {
        this.pk = pk;
        this.numero = numero;
        this.data = data;
        this.fornecedor = fornecedor;
        this.itens = itens;
    }

    public Compra(int numero, Date data, Fornecedor fornecedor, ArrayList<CompraItem> itens) {
        this.numero = numero;
        this.data = data;
        this.fornecedor = fornecedor;
        this.itens = itens;
    }

    public Compra(int numero, Date data, Fornecedor fornecedor) {
        this.numero = numero;
        this.data = data;
        this.fornecedor = fornecedor;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ArrayList<CompraItem> getItens() {
        if (this.itens==null){
            this.itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(ArrayList<CompraItem> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Compra{" + "pk=" + pk + ", numero=" + numero + ", data=" + data + ", fornecedor=" + fornecedor + ", itens=" + itens + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.pk;
        hash = 31 * hash + this.numero;
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
        final Compra other = (Compra) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }
    
    
            
    
    
        
    
}
