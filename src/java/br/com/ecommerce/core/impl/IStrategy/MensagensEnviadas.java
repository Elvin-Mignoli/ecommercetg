/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.MensagemDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;


/**
 *
 * @author Elvin
 */
public class MensagensEnviadas implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
         CaixaEntrada entrada = (CaixaEntrada) entidade;
         Resultado resultado = new Resultado();
         MensagemDAO dao = new  MensagemDAO();
        try {
            resultado.setEntidades(dao.consultarMensagensEnvidas(entrada));
            return resultado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado.setMensagemSimples("Houve algum erro inesperado");
            return null;
        }
    }
    
    
}
