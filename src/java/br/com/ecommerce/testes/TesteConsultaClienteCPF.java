/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author java
 */
public class TesteConsultaClienteCPF
{
    public static void main(String[] args)
    {
        ClienteDAO dao = new ClienteDAO();
        
        Cliente cliente = new Cliente();
        cliente.setCpf("41176861869");
        
        try
        {
            if(dao.consultaClienteCPF(cliente) != null)
            {
                System.out.println("O cliente já existe!");
            }
            else
            {
                System.out.println("Não existe ninguem cadastrado com esse CPF!");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
