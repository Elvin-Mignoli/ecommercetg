/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Monteiro
 */
public class RemoverConsultorIStrategy implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Pedido pedido = (Pedido) entidade;
        Resultado rs = new Resultado();
        PedidoDAO dao = new PedidoDAO();
        
        try
        {
            dao.removerConsultor(entidade);
            return rs;
        } 
        catch (SQLException ex)
        {
            rs.setMensagemSimples(ex.getMessage());
            return rs;
        }
    }
}
