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
 * @author Felipe Monteiro
 */
public class ValidaEmailSenha implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {        
        AutenticarDAO dao = new AutenticarDAO();
        Resultado rs = new Resultado();
        try
        {
            EntidadeDominio entity = dao.consultarUm(entidade);
            
            if(entity != null)
                rs.setEntidade(entity);
            else
                rs.setEntidade(null);
            
            return rs;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagens("Ocorreu um erro inesperado, tente mais tarde!");
            return rs;
        }
    }
} 