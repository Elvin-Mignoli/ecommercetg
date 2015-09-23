/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Interessado;
import br.com.ecommerce.domain.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ConsultaInscritosVHWeb implements IViewHelper
{
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        pedido = new Pedido();
        
        Integer id = new Integer(request.getParameter("txtId"));
        
        pedido.setId(id);
        
        Interessado inter = new Interessado();
        
        inter.setPedido(pedido);
        
        return inter;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(!resultado.getMensagens().isEmpty()) //lista de pedidos nao esta vazia??
        {
            request.setAttribute("MsgAtualiza", resultado.getMensagens());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("prestadores", resultado.getEntidades());
            request.getRequestDispatcher("ClienteInscritos.jsp").forward(request, response);
        }
    }
    
}
