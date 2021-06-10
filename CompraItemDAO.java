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
import masterbusiness.controller.CompraItem;

/**
 *
 * @author igor_
 */
public class CompraItemDAO {
    
    public static void create(CompraItem item) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        if (item.getProduto() == null || item.getProduto().getPkProduto() == 0) {
            throw new RuntimeException("ERRO: O produto não está cadastrado");
        }
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO compra_item(fk_compra, fk_produto, qtd, valor_unitario) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, item.getFk_compra());
        stm.setInt(2, item.getProduto().getPkProduto());
        stm.setInt(3, item.getQtd());
        stm.setDouble(4, item.getValor_unitario());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        item.setPk_item(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/funcionario
        
    }
    
    public static ArrayList<CompraItem> retreaveAll(int fk_compra) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                "select * from compra_item where fk_compra=" + fk_compra);
        
        ArrayList<CompraItem> aux = new ArrayList<>();
        
        while (rs.next()) {
            aux.add(new CompraItem(
                    rs.getInt("pk_item"), //o que está entre aspas deve coincidir com o mesmo nome das colunas no banco de dados
                    fk_compra,
                    ProdutoDAO.retreave(rs.getInt("fk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")));
            
        }        
        
        return aux;        
    }
    
    public static void update(CompraItem item) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE compra_item SET fk_produto=?, qtd=?, valor_unitario=? WHERE pk_item=?"
        );
        
        stm.setInt(1, item.getProduto().getPkProduto());
        stm.setInt(2, item.getQtd());
        stm.setDouble(3, item.getValor_unitario());
        stm.setInt(4, item.getPk_item());
        
        stm.execute();
        
    }
    
    public static void delete(CompraItem item) throws SQLException {
        delete(item.getPk_item());
        item.setPk_item(0);
        
    }
    
    public static void delete(int pk_item) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(
                "delete from compra_item where pk_item=" + pk_item);
        
    }
    
}
