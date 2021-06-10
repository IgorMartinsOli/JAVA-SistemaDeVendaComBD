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
public class Cliente extends PessoaFisica {

    public Cliente(String cpf, int pk, String nome) {
        super(cpf, pk, nome);
    }

    public Cliente(String cpf, String nome) {
        super(cpf, nome);
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + super.nome + '}';
    }
    
    @Override
    public void print(){
        super.print();
    }
    
}
