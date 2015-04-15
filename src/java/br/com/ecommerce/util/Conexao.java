/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Elvin
 */
public class Conexao {
      public static Connection conectar() 
            throws  ClassNotFoundException, SQLException
    {
        String url = "jdbc:mysql://localhost:3306/ecommerce"; 
        String usuario = "root"; 
        String senha = "root"; 
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection conn; 
        conn = DriverManager.getConnection(url,usuario,senha); 
        return conn;
    }
}
