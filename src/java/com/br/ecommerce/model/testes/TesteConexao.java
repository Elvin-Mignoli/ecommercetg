/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ecommerce.model.testes;

import com.br.ecommerce.model.util.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class TesteConexao {
     public static void main(String[] args)
    {
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            if(conn != null)
                System.out.println("Conexão estabelecida com sucesso");
            else
               System.out.println("Falha na conexão ");
            conn.close();
                    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TesteConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
