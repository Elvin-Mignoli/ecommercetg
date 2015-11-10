/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.AtualizarStatusPedidoStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class FecharPedidoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Pedido pedido = new Pedido();
        pedido.setPrestadorFinalista(new PrestadorServico());
        pedido.setStatus(Status.EM_PROCESSO);
        
        pedido.setId(new Integer(request.getParameter("txtIdPedido")));
        pedido.setStatus(Status.EM_PROCESSO);
        pedido.getPrestadorFinalista().setId(new Integer(request.getParameter("txtIdPrestador")));
        IStrategy strategy = new AtualizarStatusPedidoStrategy();
        
        Resultado rs = strategy.processar(pedido);
        
        if(rs.getMensagemSimples() != null) //existe uma mensagem de erro?
        {
            response.getWriter().write(rs.getMensagemSimples());
        }
        else
        {
            response.getWriter().write("");
        }
    }
    
}
