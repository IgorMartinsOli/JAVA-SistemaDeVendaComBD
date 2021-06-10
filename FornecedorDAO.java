/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import masterbusiness.controller.Endereco;
import masterbusiness.controller.Fornecedor;

/**
 *
 * @author igor_
 */
public class FornecedorDAO {
    
    public static void create(Fornecedor f) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "INSERT INTO cliente(nome, cpf) VALUES (?,?)", 
             PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCnpj());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        f.setPk(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/fornecedor
        
        
        for (Endereco e : f.getEnderecos()){
            e.setFk(f.getPk());//atribuindo a chave primária do fornecedor que acabou de ser inserino na chave extrangeira do endereço
            FornecedorEnderecoDAO.create(e);
        }
    }
    
    public static Fornecedor retreave(int pk_fornecedor) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from cliente where pk_cliente" + pk_fornecedor);
        
        Fornecedor f;
        
        if (rs.next()){
            
            f = new Fornecedor(
                        rs.getString("cpf"),//nome da coluna no banco de dados 
                        rs.getString("nome"));
            
            f.setEnderecos(FornecedorEnderecoDAO.retreaveAll(pk_fornecedor));
        } else {
            throw new RuntimeException("Cliente com a chave " + pk_fornecedor + " não existe");
        }
        
        return f;        
    }
    
    public static ArrayList<Fornecedor> retreaveAll()throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from fornecedor");
        
        ArrayList<Fornecedor> fornecedors = new ArrayList<>();
        
        
        
        while(rs.next()){
            
            Fornecedor f = new Fornecedor(
                        rs.getInt("pk_fornecedor"),
                        rs.getString("cnpj"),
                        rs.getString("nome"));
            
            f.setEnderecos(FornecedorEnderecoDAO.retreaveAll(rs.getInt("pk_cliente")));
            
            fornecedors.add(f);
        }
        
        return fornecedors;
        
    }
    
    public static void update(Fornecedor f)throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE cliente SET nome=?, cpf=?	WHERE pk_cliente=?"
        );
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCnpj());
        stm.setInt(3, f.getPk());
        
        stm.execute();
        
        for (Endereco e: f.getEnderecos()){
            if (!e.isPersistido()){
               FornecedorEnderecoDAO.update(e);
            }
        }
    }
    
    public static void delete(Fornecedor f) throws SQLException{
       delete(f.getPk());
       f.setPk(0);
    }
    
    public static void delete(int pk_fornecedor) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(                   
                "delete from fornecedor where pk_cliente=" + pk_fornecedor);
    }
}
