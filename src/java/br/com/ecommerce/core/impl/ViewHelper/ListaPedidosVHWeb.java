/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
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
public class ListaPedidosVHWeb implements IViewHelper
{
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        pedido = new Pedido();
        pedido.setConsulta(Pedido.CLIENTE);
        pedido.setCliente(new Cliente());
        pedido.getCliente().setId(new Integer(request.getParameter("txtId")));
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(!resultado.getMensagens().isEmpty()) //a lista de mensagens de erro não é vazia?
        {
            request.setAttribute("MsgAtualiza", "Tente executar essa função mais tarde!");
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("pedidos",resultado.getEntidades());
            request.getRequestDispatcher("ClientePedidos.jsp").forward(request, response);
        }
    }
    
}
