/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;
/**
 *
 * @author java
 */
public class ExisteCliente implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Cliente cliente = (Cliente) entidade;
        Usuario usuario = (Usuario) entidade;
        Resultado resultado = new Resultado();
        
        ClienteDAO dao = new ClienteDAO();
        
        try
        {
            if(dao.consultaClienteCPF(entidade) == null)
            {
                return resultado;
            }
            else
            {
                resultado.addMensagens("Já existe um cliente com esse CPF!");
                return resultado;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Algum erro inesperado ocorreu!");
            return resultado;
        }
    }
}
