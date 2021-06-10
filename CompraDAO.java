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
import masterbusiness.controller.Compra;
import masterbusiness.controller.CompraItem;
import masterbusiness.controller.Endereco;
import masterbusiness.controller.Funcionario;

/**
 *
 * @author igor_
 */
public class CompraDAO {
    
    public static void create(Compra c) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        if (c.getFornecedor()==null || c.getFornecedor().getPk()==0){
            throw new RuntimeException("ERRO: Fornecedor não está cadastrado");
        }
        
        if (c.getItens().isEmpty()){//testa se o array list está vazio (mesmo que size==0)
            throw new RuntimeException("ERRO: A compra precisa possuir ao menos um item cadastrado");
        }


        PreparedStatement stm = conn.prepareStatement(
             "INSERT INTO compra(fk_fornecedor, numero, data) VALUES (?, ?, ?)", 
             PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, c.getFornecedor().getPk());
        stm.setInt(2, c.getNumero());
        stm.setDate(3, c.getData());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        c.setPk(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/funcionario
        
        
        for (CompraItem i: c.getItens()){
            i.setFk_compra(c.getPk());//atribuindo a chave primária do funcionario que acabou de ser inserino na chave extrangeira do endereço
            CompraItemDAO.create(i);
        }
    }
    
    public static Compra retreave(int pk_compra) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from compra where pk_compra=" + pk_compra);
        
        Compra c;
        
        if (rs.next()){
            
            c = new Compra(
                    pk_compra,
                    rs.getInt("numero"), 
                    rs.getDate("data"), 
                    FornecedorDAO.retreave(rs.getInt("fk_fornecedor")), 
                    CompraItemDAO.retreaveAll(pk_compra));
            
            
        } else {
            throw new RuntimeException("Compra não existe");
        }
        
        return c;        
    }
    
    public static ArrayList<Compra> retreaveAll()throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from compra");
        
        ArrayList<Compra> compras = new ArrayList<>();
        
        while(rs.next()){
            
            compras.add(new Compra(
                    rs.getInt("pk_compra"),
                    rs.getInt("numero"), 
                    rs.getDate("data"), 
                    FornecedorDAO.retreave(rs.getInt("fk_fornecedor")), 
                    CompraItemDAO.retreaveAll(rs.getInt("pk_compra"))));
            
        }    
        
        return compras;        
        
    }
    
    
    public static void update(Compra c)throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE compra SET fk_fornecedor=?, numero=?, data=? WHERE pk_compra=?"   
        );
        
        stm.setInt(1, c.getFornecedor().getPk());
        stm.setInt(2, c.getNumero());
        stm.setDate(3, c.getData());
        stm.setInt(4, c.getPk());
        
        stm.execute();
        
        for (CompraItem i: c.getItens()){
               CompraItemDAO.update(i);
        }
    }
    
    public static void delete(Compra c) throws SQLException{
       delete(c.getPk());
       c.setPk(0);
    
    }
    public static void delete(int pk_compra) throws SQLException{

        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(
                               
                "delete from compra where pk_compra=" + pk_compra);

    }    
}
