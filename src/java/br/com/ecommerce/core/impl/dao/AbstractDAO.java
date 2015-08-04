/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public abstract class AbstractDAO implements IDAO
{
    protected Connection conexao;
    protected PreparedStatement pst;
    protected boolean transaction = false;
    
    public AbstractDAO(){ } //construtor default
    
    /**
     * Construtor para compartilhamento de conexao
     * @param conexao  - Conexao que vai ser compartilhada com uma classe DAO
     */
    public AbstractDAO(Connection conexao) 
    {
        this.conexao = conexao;
    }
    
    
    protected void openConnection()
    {
        try
        {
            conexao = Conexao.conectar();  
            transaction = true;
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Class Not Found Exception");
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQLException");
        }
    }
}
