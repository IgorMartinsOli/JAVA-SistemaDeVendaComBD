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

/**
 *
 * @author igor_
 */
public class FuncionarioEndereoDAO {

    public static void create(Endereco e) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO funcionario_endereco(fk_funcionario, logradouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );

        stm.setInt(1, e.getFk());
        stm.setString(2, e.getLogradouro());
        stm.setString(3, e.getBairro());
        stm.setString(4, e.getCidade());
        stm.setString(5, e.getEstado());
        stm.setString(6, e.getPais());
        stm.setString(7, e.getCep());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();//retorna a chave primária gerada
        rs.next();

        e.setPk(rs.getInt(1));
        e.setPersistido(true);

        stm.close();
    }

    public static ArrayList<Endereco> retreaveAll(int fk_funcionario) throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from funcionario_endereco where fk_funcionario =" + fk_funcionario
        );

        ArrayList<Endereco> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_funcionario_endereco"),
                    fk_funcionario));
        }

        return aux;
    }

    public static ArrayList<Endereco> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(
                "select * from funcionario_endereco"
        );

        ArrayList<Endereco> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_funcionario_endereco"),
                    rs.getInt("fk_funcionario")));
        }

        return aux;
    }
    
    public static void update(Endereco e)throws SQLException{
        if (e.getPk()==0){
            throw new RuntimeException("Esse endereço nunca foi cadastrado para ser alterado");
        }
        
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE funcionario_endereco SET logradouro=?, bairro=?, cidade=?, estado=?, pais=?, cep=? WHERE pk_funcionario_endereco=?"
        );

        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getBairro());
        stm.setString(3, e.getCidade());
        stm.setString(4, e.getEstado());
        stm.setString(5, e.getPais());
        stm.setString(6, e.getCep());
        stm.setInt(7, e.getPk());
        
        
        stm.execute();
        stm.close();
        e.setPersistido(true);
    }
    
    public static void delete(Endereco e) throws SQLException{
        delete(e.getPk());
        e.setPk(0);
    }
    
    public static void delete(int pk_endereco) throws SQLException {
        
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
        "delete from funcionario_endereco where pk_funcionario_endereco = ?"
        );
        
        stm.setInt(1, pk_endereco);
        
        stm.execute();
        stm.close();
    }    

}
