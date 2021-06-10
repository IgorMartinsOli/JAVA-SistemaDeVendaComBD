/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import masterbusiness.controller.Venda;
import java.sql.SQLException;
import java.util.ArrayList;
import masterbusiness.controller.VendaItem;

/**
 *
 * @author igor_
 */
public class VendaDAO {
    public static void create(Venda v) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        if (v.getFk_fornecedor()==null || v.getFk_fornecedor().getPk()==0){
            throw new RuntimeException("ERRO: Fornecedor não está cadastrado");
        }
        
        if (v.getItens().isEmpty()){//testa se o array list está vazio (mesmo que size==0)
            throw new RuntimeException("ERRO: A venda precisa possuir ao menos um item cadastrado");
        }
        
        PreparedStatement stm = conn.prepareStatement(
             "INSERT INTO venda(fk_cliente, fk_vendedor, numero, data) VALUES (?, ?, ?, ?)", 
             PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, v.getFk_cliente().getPk());
        stm.setInt(2, v.getFk_fornecedor().getPk());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, v.getData());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        v.setPk_venda(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/funcionario
        
        
        for (VendaItem i: v.getItens()){
            i.setFk_venda(v.getPk_venda());//atribuindo a chave primária do funcionario que acabou de ser inserino na chave extrangeira do endereço
            VendaItemDAO.create(i);
        }
    }
    
    public static Venda retreave(int pk_venda) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from venda where pk_venda=" + pk_venda);
        
        Venda v;
        
        if (rs.next()){
            
            
            //int pk_venda, Cliente fk_cliente, Fornecedor fk_fornecedor, int numero, Date data, ArrayList<VendaItem> itens
                    
                    
            v = new Venda(
                    pk_venda,
                    ClienteDAO.retreave(rs.getInt("fk_cliente")),
                    FornecedorDAO.retreave(rs.getInt("fk_fornecedor")), 
                    rs.getInt("numero"),
                    rs.getDate("data"), 
                    VendaItemDAO.retreaveAll(pk_venda));
            
            
        } else {
            throw new RuntimeException("Compra não existe");
        }
        return v;        
    }
    
    public static ArrayList<Venda> retreaveAll()throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from venda");
        
        ArrayList<Venda> vendas = new ArrayList<>();
        
        while(rs.next()){
            
            vendas.add(new Venda(
                    rs.getInt("pk_venda"),
                    ClienteDAO.retreave(rs.getInt("fk_cliente")),
                    FornecedorDAO.retreave(rs.getInt("fk_fornecedor")),
                    rs.getInt("numero"),
                    rs.getDate("data"),
                    VendaItemDAO.retreaveAll(rs.getInt("pk_venda"));
        }        
        return vendas;
    }
    
    
    public static void update(Venda v)throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE venda SET fk_cliente=?, fk_vendedor=?, numero=?, data=? WHERE pk_compra=?"  
        );
        
        stm.setInt(1, v.getFk_cliente().getPk());
        stm.setInt(2, v.getFk_fornecedor().getPk());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, v.getData());
        stm.setInt(5, v.getPk_venda());
        
        stm.execute();
        
        for (VendaItem i: v.getItens()){
               VendaItemDAO.update(i);
        }
    }
    
    public static void delete(Venda v) throws SQLException{
       delete(v.getPk_venda());
       v.setPk_venda(0);
    
    }
    public static void delete(int pk_venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(                  
            "delete from venda where pk_compra=" + pk_venda);
    }
}
