/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author java
 */
public class TesteUsuarioEmailDAO
{
    public static void main(String[] args) throws SQLException
    {
        AutenticarDAO dao = new AutenticarDAO();
        Usuario us = new Cliente();
        us.setUsuarioID(2);
        us.setEmail("felipe.arruda.melo@gmail.com");
        
        dao.atualizaEmail(us);
        
    }
}
