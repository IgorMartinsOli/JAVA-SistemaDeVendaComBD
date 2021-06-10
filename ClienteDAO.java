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
import masterbusiness.controller.Cliente;

/**
 *
 * @author igor_
 */
public class ClienteDAO {
    
    public static void create(Cliente c) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "INSERT INTO cliente(nome, cpf) VALUES (?,?)", 
             PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        c.setPk(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/cliente
        
        
        for (Endereco e : c.getEnderecos()){
            e.setFk(c.getPk());//atribuindo a chave primária do cliente que acabou de ser inserino na chave extrangeira do endereço
            ClienteEnderecoDAO.create(e);
        }
    }
    
    public static Cliente retreave(int pk_cliente) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from cliente where pk_cliente" + pk_cliente);
        
        Cliente c;
        
        if (rs.next()){
            
            c = new Cliente(
                        rs.getString("cpf"),//nome da coluna no banco de dados 
                        rs.getString("nome"));
            
            c.setEnderecos(ClienteEnderecoDAO.retreaveAll(pk_cliente));
        } else {
            throw new RuntimeException("Cliente com a chave " + pk_cliente + " não existe");
        }
        
        return c;        
    }
    
    public static ArrayList<Cliente> retreaveAll()throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from cliente");
        
        ArrayList<Cliente> clients = new ArrayList<>();
        
        
        
        while(rs.next()){
            
            Cliente c = new Cliente(
                        rs.getString("cpf"),
                        rs.getInt("pk_cliente"),
                        rs.getString("nome"));
            
            c.setEnderecos(ClienteEnderecoDAO.retreaveAll(rs.getInt("pk_cliente")));
            
            clients.add(c);
        }    
        
        return clients;
        
    }
    
    public static void update(Cliente c)throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE cliente SET nome=?, cpf=?	WHERE pk_cliente=?"
        );
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.setInt(3, c.getPk());
        
        stm.execute();
        
        for (Endereco e: c.getEnderecos()){
            if (!e.isPersistido()){
               ClienteEnderecoDAO.update(e);
            }
        }
    }
    
    public static void delete(Cliente c) throws SQLException{
       delete(c.getPk());
       c.setPk(0);
    
    }
    public static void delete(int pk_cliente) throws SQLException{

        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(
                               
                "delete from cliente where pk_cliente=" + pk_cliente);

    }
    
}
