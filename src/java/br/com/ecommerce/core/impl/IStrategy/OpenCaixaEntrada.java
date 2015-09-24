/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.core.impl.dao.ClienteDAO;
import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class OpenCaixaEntrada implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Resultado resultado = new Resultado();
        if(entidade instanceof Cliente)
        {
            ClienteDAO dao = new ClienteDAO();
            try {
                EntidadeDominio ent =dao.consultarUm(entidade);
                if(ent != null)
                     resultado.setEntidade(ent);
                else
                    resultado.setEntidade(null);
                return resultado;
            } catch (SQLException ex) {
                ex.printStackTrace();
                resultado.getMensagens().add("Houve algum erro inesperado");
                return null;
            }
        }else if(entidade instanceof PrestadorServico)
        {
            PrestadorServicoDAO dao = new PrestadorServicoDAO();
            try {
                EntidadeDominio ent =dao.consultarUm(entidade);
                if(ent != null)
                     resultado.setEntidade(ent);
                else
                    resultado.setEntidade(null);
                return resultado;
            } catch (SQLException ex) {
                ex.printStackTrace();
                resultado.getMensagens().add("Houve algum erro inesperado");
                return null;
            }
            
        }
        return null;
    }

}
