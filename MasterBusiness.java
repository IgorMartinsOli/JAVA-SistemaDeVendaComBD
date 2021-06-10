/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import masterbusiness.controller.Compra;
import masterbusiness.controller.CompraItem;
import masterbusiness.controller.Endereco;
import masterbusiness.controller.Funcionario;
import masterbusiness.controller.Produto;
import masterbusiness.model.CargoDAO;
import masterbusiness.model.CompraDAO;
import masterbusiness.model.CompraItemDAO;
import masterbusiness.model.FornecedorDAO;
import masterbusiness.model.FuncionarioDAO;
import masterbusiness.model.ProdutoDAO;

/**
 *
 * @author igor_
 */
public class MasterBusiness {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
                    
        try {
            
             

            
            Compra c = CompraDAO.retreave(4);
            
            
            CompraDAO.delete(c);
            
//            c.setNumero(777777);
//            
//            c.getItens().get(0).setQtd(1000);
//            c.getItens().get(1).setValor_unitario(500);
//            
//            CompraDAO.update(c);
            

            
//            Compra c = new Compra(
//                    335677, 
//                    java.sql.Date.valueOf(LocalDate.now()), 
//                    FornecedorDAO.retreave(2));
//            
//            
//            c.getItens().add(new CompraItem(
//                    ProdutoDAO.retreave(1), 
//                    3, 
//                    70));
//            
//            c.getItens().add(new CompraItem(
//                    ProdutoDAO.retreave(3), 
//                    7, 
//                    350));
//            
//            CompraDAO.create(c);
            
        
            
            
            
//            Funcionario func = new Funcionario(
//                    CargoDAO.retreave(4), 
//                    "2339923274", 
//                    "Robervaldo");
//            
//            
//            func.getEnderecos().add(new Endereco(
//            "Rua das Arcadianas",
//            "Setor X",
//            "Goiânia",
//            "GO",
//            "Brasil",
//            "7400000"
//            ));
//            
//            
//            func.getEnderecos().add(new Endereco(
//            "Alameda dos Ipês",
//            "Setor Aeroporto",
//            "Caldas Novas",
//            "GO",
//            "Brasil",
//            "7600000"
//            ));            
//            
//            
//            
//            FuncionarioDAO.create(func);
            
        } catch (SQLException ex) {
            Logger.getLogger(MasterBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            

        
        
    }

}
