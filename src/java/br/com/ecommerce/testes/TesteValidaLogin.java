/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author java
 */
public class TesteValidaLogin
{

    public static void main(String[] args) throws SQLException
    {
        Usuario usuario = new Cliente();

        usuario.setEmail("felipe.cife@gmail.com");
        usuario.setSenha("admin");

        AutenticarDAO dao = new AutenticarDAO();

        EntidadeDominio entity = dao.consultarUm(usuario);

        if (entity instanceof Cliente)
        {
            System.out.println("Cliente");
            Cliente c = (Cliente) entity;
            System.out.println("Endereco: "+c.getEndereco().getId());
        } else if (entity instanceof PrestadorServico)
        {
            System.out.println("Prestador");
        } else if (entity == null)
        {
            System.out.println("Deu merda!");
        }
    }
}
