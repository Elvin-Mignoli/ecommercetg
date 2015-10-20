/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.CaixaEntradaDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public class OpenCaixaEntrada implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Resultado resultado = new Resultado();
        Usuario user = (Usuario) entidade;
        
        if (entidade instanceof Cliente)
        {
            CaixaEntradaDAO dao = new CaixaEntradaDAO();
            try
            {
                EntidadeDominio ent = dao.consultarUm(user.getEntrada());
                if (ent != null)
                {
                    resultado.setEntidade(ent);
                } else
                {
                    resultado.setEntidade(null);
                }
                return resultado;
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                resultado.setMensagemSimples("Houve algum erro inesperado");
                return resultado;
            }
        }
        else if (entidade instanceof PrestadorServico)
        {
            CaixaEntradaDAO dao = new CaixaEntradaDAO();
            try
            {
                EntidadeDominio ent = dao.consultarUm(user.getEntrada());
                if (ent != null)
                {
                    resultado.setEntidade(ent);
                } else
                {
                    resultado.setEntidade(null);
                }
                return resultado;
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
                resultado.setMensagemSimples("Houve algum erro inesperado");
                return resultado;
            }
        }
        return resultado;
    }

}
