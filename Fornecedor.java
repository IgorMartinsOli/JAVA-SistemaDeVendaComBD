/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.controller;

/**
 *
 * @author igor_
 */
public class Fornecedor extends PessoaJuridica{

    public Fornecedor(String cnpj, String nome) {
        super(cnpj, nome);
    }

    public Fornecedor(int pk, String cnpj, String nome) {
        super(pk, cnpj, nome);
    }
}
