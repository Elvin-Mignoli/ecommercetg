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
 * ATENÇÃO !!!! --> Sempre utilizar o driver da IDE para não termos problema de compatibilidade
 */
public class Conexao
{
    public static Connection conectar() throws ClassNotFoundException, SQLException
    {
        /*Conexao com o banco online*
        String url = "jdbc:postgresql://pgsql.knowhow.kinghost.net/knowhow";
        String usuario = "knowhow";
        String senha = "c8naydes1012";*/
        
        
        //Conexao com o banco local
        String url = "jdbc:postgresql://localhost:5432/Ecommerce";
        String usuario = "postgres";
        //senha deve ser padrão em todas as máquinas
        String senha = "admin";
        
        Class.forName("org.postgresql.Driver");
        Connection conn;
        conn = DriverManager.getConnection(url, usuario, senha);
        return conn;
    }
}
