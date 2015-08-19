/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;

import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public class ExistePrestador implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        PrestadorServico prestador = (PrestadorServico) entidade;
        Usuario usuario = (Usuario) entidade;
        Resultado resultado = new Resultado();
        
        PrestadorServicoDAO dao = new PrestadorServicoDAO();
        
        try
        {
            if(dao.consultaPrestadorCPF(entidade) == null)
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
