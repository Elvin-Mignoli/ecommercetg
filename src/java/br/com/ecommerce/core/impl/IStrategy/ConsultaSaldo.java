/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.TransacaoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;

/**
 *
 * @author Felipe Monteiro
 */
public class ConsultaSaldo implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Resultado rs = new Resultado();
        try
        {
            TransacaoDAO dao = new TransacaoDAO();
            
            //consultando saldo para uma transacao
            if(!dao.consultaSaldo(entidade))
            {
                rs.addMensagens("Pedido recusado pelo banco");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagens("Erro ao processar a requisição");
        }
        return rs;
    }
    
}
