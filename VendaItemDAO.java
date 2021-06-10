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
import masterbusiness.controller.VendaItem;

/**
 *
 * @author igor_
 */
public class VendaItemDAO {
    
    public static void create(VendaItem item) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        if (item.getFk_produto() == null || item.getFk_produto().getPkProduto() == 0) {
            throw new RuntimeException("ERRO: O produto não está cadastrado");
        }
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO venda_item(fk_venda, fk_produto, qtd, valor_unitario) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, item.getFk_venda());
        stm.setInt(2, item.getFk_produto().getPkProduto());
        stm.setInt(3, item.getQtd());
        stm.setDouble(4, item.getValor_unitario());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        item.setPk_item(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/funcionario
        
    }
    
    public static ArrayList<VendaItem> retreaveAll(int fk_venda) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                "select * from venda_item where fk_venda=" + fk_venda);
        
        ArrayList<VendaItem> aux = new ArrayList<>();
        
        while (rs.next()) {
            
            aux.add(new VendaItem(
                    rs.getInt("pk_item"), //o que está entre aspas deve coincidir com o mesmo nome das colunas no banco de dados
                    fk_venda,
                    ProdutoDAO.retreave(rs.getInt("fk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")));
        }
        return aux;        
    }
    
    public static void update(VendaItem item) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE venda_item SET fk_venda=?, fk_produto=?, qtd=?, valor_unitario=? WHERE pk_item=?"
        );
        
        stm.setInt(1, item.getFk_venda());
        stm.setInt(2, item.getFk_produto().getPkProduto());
        stm.setInt(3, item.getQtd());
        stm.setDouble(4, item.getValor_unitario());
        stm.setInt(5, item.getPk_item());
        
        stm.execute();
    }
    
    public static void delete(VendaItem item) throws SQLException {
        delete(item.getPk_item());
        item.setPk_item(0);
    }
    
    public static void delete(int pk_item) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(
                "delete from venda_item where pk_item=" + pk_item);
    }
    
}
