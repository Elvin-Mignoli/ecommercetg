/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author java
 */
public class TesteExisteEmail
{
    public static void main(String[] args)
    {
        Resultado resultado = new Resultado();
        AutenticarDAO dao = new AutenticarDAO();
        Cliente cliente = new Cliente();
        cliente.setEmail("felipe.cife@gmail.com");
        
        try
        {
            if(dao.existeEmail(cliente) == null)
            {
                System.out.println("Email disponível");
            }
            else
            {
                System.out.println("Email indisponível");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Erro ao consultar entidade!");
        }
    }
}
