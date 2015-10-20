/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.MensagemDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;

/**
 *
 * @author Felipe Monteiro
 */
public class AlterarStatusMsgIStrategy implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Resultado rs = null;
        try
        {
            MensagemDAO dao = new MensagemDAO();

            dao.AtualizaMensagemAberta(entidade);
            
            return null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            rs = new Resultado();
            rs.setMensagemSimples("Erro ao alterar status da mensagem");
            
            return rs;
        }
    }
}
