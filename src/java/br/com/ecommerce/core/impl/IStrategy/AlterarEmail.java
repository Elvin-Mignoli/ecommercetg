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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class AlterarEmail implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        AutenticarDAO dao = new AutenticarDAO();
        Resultado rs = new Resultado();
        
        try {
            dao.alterarEmail(entidade);
            return rs;
        } catch (SQLException ex) {
            rs.setMensagemSimples(ex.getMessage());
            return rs;
        }
    }
    
}
