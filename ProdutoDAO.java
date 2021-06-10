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
import masterbusiness.controller.Produto;

/**
 *
 * @author igor_
 */
public class ProdutoDAO {

    public static void create(Produto p) throws SQLException {

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO produto(nome, estoque_minimo, qtd_estoque) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );

        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoqueMinimo());
        stm.setInt(3, p.getQtdEstoque());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();// joga o cursor do resultset (tabela) para frente e retorna true se essa nova posição for válida

        p.setPkProduto(rs.getInt(1));

        stm.close();
    }

    public static Produto retreave(int pkProduto) throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from produto where pk_produto = " + pkProduto
        );

        rs.next();

        return new Produto(pkProduto,
                rs.getString("nome"),//pegue o valor da coluna 'nome' e converta para string 
                rs.getInt("estoque_minimo"),//pegue o valor da coluna 'estoque_minimo' e converta para int
                rs.getInt("qtd_estoque"));
    }

    public static ArrayList<Produto> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from produto"
        );

        ArrayList<Produto> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(
                    new Produto(rs.getInt("pk_produto"),
                            rs.getString("nome"),
                            rs.getInt("estoque_minimo"),
                            rs.getInt("qtd_estoque")
            ));
        }
               
        return aux;
    }
    
    public static ArrayList<Produto> retreaveProdutosAbaixoEstoqueMinimo() throws SQLException{
        
        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from produto where qtd_estoque < estoque_minimo"
        );

        ArrayList<Produto> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(
                    new Produto(rs.getInt("pk_produto"),
                            rs.getString("nome"),
                            rs.getInt("estoque_minimo"),
                            rs.getInt("qtd_estoque")
            ));
        }
        
        return aux;
    }
    
    public static ArrayList<Produto> retreaveByName(String nome) throws SQLException{
        
        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from produto where nome like '" + nome + "%'"
        );

        ArrayList<Produto> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(
                    new Produto(rs.getInt("pk_produto"),
                            rs.getString("nome"),
                            rs.getInt("estoque_minimo"),
                            rs.getInt("qtd_estoque")
            ));
        }
        
        return aux;
    }
    
    //public static ArrayList<Produto> retreaveByEstoque(int estoqueInicial, int estoqueFinal)//retornar todos os produtos que contam com o estoque nesse intervalo, inclusive
    //public static ArrayList<Produto> retreaveByEstoqueMinimo(int estoqueInicial, int estoqueFinal)//retornar todos os produtos que contam com o estoque nesse intervalo, inclusive
    
    public static void update(Produto p) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
            "update produto set nome=?, estoque_minimo=?, qtd_estoque=? where pk_produto = ?"
        );
        
        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoqueMinimo());
        stm.setInt(3, p.getQtdEstoque());
        stm.setInt(4, p.getPkProduto());

        
        stm.executeUpdate();
        stm.close();
    }
    
    public static void delete(Produto p)throws SQLException{
        delete(p.getPkProduto());
        p.setPkProduto(0);
    }
    
    public static void delete(int pk_produto)throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("delete from produto where pk_produto = " + pk_produto);
    }

}
