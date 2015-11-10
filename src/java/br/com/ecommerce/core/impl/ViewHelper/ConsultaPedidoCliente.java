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
import br.com.ecommerce.core.impl.IStrategy.HistoricoMensagem;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ConsultaPedidoCliente implements IViewHelper
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
        
        pedido.setId(new Integer(request.getParameter("txtId")));
        
        IStrategy st = new ConsultaUmPedidoClienteIStrategy();
        
        Cliente cliente = (Cliente) request.getSession().getAttribute("user");
        
        pedido.setCliente(cliente);
        
        Resultado rs = st.processar(pedido);
        pedido = (Pedido) rs.getEntidade();
        if(!pedido.getStatus().toString().equals("ABERTO"))
        {
            if(!pedido.getStatus().toString().equals("CANCELADO"))
            {
                //buscar o histórico da mensagens
                cliente.setPedido((Pedido) rs.getEntidade());
                HistoricoMensagem historico = new HistoricoMensagem();
                resultado = historico.processar(cliente);
            }
        } 
        
        
        if(rs.getMensagemSimples() != null) //tem alguma mensagem de erro?
        {
            request.setAttribute("MsgAtualiza", rs.getMensagemSimples());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("pedido", rs.getEntidade());
            if(resultado != null)
                request.setAttribute("mensagens",resultado.getEntidades());
            request.getRequestDispatcher("ClienteDetalhePedido.jsp").forward(request, response);
        }
    }
    
}
