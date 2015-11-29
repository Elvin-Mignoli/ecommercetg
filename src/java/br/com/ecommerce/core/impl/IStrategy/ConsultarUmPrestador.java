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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author java
 */
public class ConsultarUmPrestador implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Resultado rs = new Resultado();
        try
        {
            PrestadorServicoDAO dao = new PrestadorServicoDAO();
            
            EntidadeDominio novaEntidade = dao.consultarUm(entidade);
            
            rs.setEntidade(entidade);
            
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.setMensagemSimples("Algum erro ocorreu ao consultar o prestador!");
            return rs;
        }
    }
    
}
