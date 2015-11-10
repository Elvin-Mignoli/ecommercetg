/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class ExcluirPedidoVHWeb implements IViewHelper
{
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        pedido = new Pedido();
        
        pedido.setId(new Integer(request.getParameter("txtId")));
        
        return pedido;
    }

    @Override
    public void setView(Resultado rs, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(!rs.getMensagens().isEmpty()) //lista de mensagens não é vazia??
        {
            request.setAttribute("MsgAtualiza", rs.getMensagens());
        }
        else
        {
            request.setAttribute("MsgAtualiza","Pedido cancelado com sucesso!");
        }
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
    
}
