/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterbusiness.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor_
 */
public class BancoDados {

    private static Connection conn;

    public static Connection createConnection() {

        if (conn == null) {
            try {

                //instancia a classe Driver do banco de dados utilizado, nesse caso o postgresql, para a implementação da classe java.sql.Driver
                Class.forName(BancoDadosConfig.DRIVER);

                //estabelece uma conexão com o banco de dados informado
                //url é o endereço do banco de dados (específico para cada tipo de banco de dados)
                //usuario
                //senha
                conn = DriverManager.getConnection(
                        BancoDadosConfig.URL,
                        BancoDadosConfig.USER,
                        BancoDadosConfig.PWD
                );
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return conn;

    }

}
