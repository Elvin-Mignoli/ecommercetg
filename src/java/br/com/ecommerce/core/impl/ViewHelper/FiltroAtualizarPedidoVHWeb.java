/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ConsultaUmPedidoClienteIStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class FiltroAtualizarPedidoVHWeb implements IViewHelper
{

    private Pedido pedido;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        pedido = new Pedido();  //instanciando pedido

        Integer id = new Integer(request.getParameter("txtId"));

        pedido.setId(id);
        
        IStrategy str = new ConsultaUmPedidoClienteIStrategy();
        
        Resultado rs = str.processar(pedido);
        
        if(rs.getMensagemSimples() != null )
        {
            request.setAttribute("MsgAtualiza", rs.getMensagemSimples());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("pedido", rs.getEntidade());
            request.getRequestDispatcher("ClienteAtualizarPedido.jsp").forward(request, response);   
        }
    }

}
