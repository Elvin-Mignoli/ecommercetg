/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;

/**
 *
 * @author java
 */
public class ExisteEmail implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        AutenticarDAO dao = new AutenticarDAO();
        Resultado rs = new Resultado();
        try
        {
            if(dao.existeEmail(entidade) != null)            
            {
                rs.setMensagemSimples("Já existe alguém com esse Email!");
                return rs;
            }
            else
            {
                return rs;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
}
