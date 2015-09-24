/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.InteressadoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;


/**
 *
 * @author Elvin
 */
public class Candidatar implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Resultado rs=  new Resultado();
        
        InteressadoDAO dao = new InteressadoDAO();
        try {
            dao.salvar(entidade);
        } catch (SQLException ex) {
            ex.printStackTrace();
            rs.getMensagens().add("Houve algum problema no servidor, tente mais tarde!!!");
        }
        return rs;
    }
    
}
