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
import masterbusiness.controller.Funcionario;

/**
 *
 * @author igor_
 */
public class FuncionarioDAO {
    
    public static void create(Funcionario f) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        if (f.getCargo()==null || f.getCargo().getPkCargo()==0){
            throw new RuntimeException("ERRO: Cargo não está cadastrado");
        }
        
        PreparedStatement stm = conn.prepareStatement(
             "INSERT INTO funcionario(fk_cargo, nome, cpf) VALUES (?,?,?)", 
             PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, f.getCargo().getPkCargo());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        f.setPk(rs.getInt(1));//recuperando a chave primária que acabou de ser gerada durante a inserção e atribuindo a propriedade 'pk' da classe pessoa/pessoafisica/funcionario
        
        
        for (Endereco e : f.getEnderecos()){
            e.setFk(f.getPk());//atribuindo a chave primária do funcionario que acabou de ser inserino na chave extrangeira do endereço
            FuncionarioEndereoDAO.create(e);
        }
    }
    
    public static Funcionario retreave(int pk_funcionario) throws SQLException{
        
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from funcionario where pk_funcionario=" + pk_funcionario);
        
        Funcionario f;
        
        if (rs.next()){
            
            f = new Funcionario(
                        CargoDAO.retreave(rs.getInt("fk_cargo")),//nome da coluna no banco de dados 
                        rs.getString("cpf"),
                        pk_funcionario,
                        rs.getString("nome"));
            
            f.setEnderecos(FuncionarioEndereoDAO.retreaveAll(pk_funcionario));
        } else {
            throw new RuntimeException("Funcionário com a chave " + pk_funcionario + " não existe");
        }
        
        return f;        
    }
    
    public static ArrayList<Funcionario> retreaveAll()throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(
                               
                "select * from funcionario");
        
        ArrayList<Funcionario> funcs = new ArrayList<>();
        
        
        
        while(rs.next()){
            
            Funcionario f = new Funcionario(
                        CargoDAO.retreave(rs.getInt("fk_cargo")),//nome da coluna no banco de dados 
                        rs.getString("cpf"),
                        rs.getInt("pk_funcionario"),
                        rs.getString("nome"));
            
            f.setEnderecos(FuncionarioEndereoDAO.retreaveAll(rs.getInt("pk_funcionario")));
            
            funcs.add(f);
        }    
        
        return funcs;        
        
    }
    
    
    public static void update(Funcionario f)throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
             "UPDATE funcionario SET fk_cargo=?, nome=?, cpf=?	WHERE pk_funcionario=?"   
        );
        
        stm.setInt(1, f.getCargo().getPkCargo());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        stm.setInt(4, f.getPk());
        
        stm.execute();
        
        for (Endereco e: f.getEnderecos()){
            if (!e.isPersistido()){
               FuncionarioEndereoDAO.update(e);
            }
        }
    }
    
    public static void delete(Funcionario f) throws SQLException{
       delete(f.getPk());
       f.setPk(0);
    
    }
    public static void delete(int pk_funcionario) throws SQLException{

        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute(
                               
                "delete from funcionario where pk_funcionario=" + pk_funcionario);

    }
    
}
