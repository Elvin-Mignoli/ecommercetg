/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.BuscaPedidoMesIStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class BuscaPedidoMesVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BuscaPedidoMesIStrategy strategy = new BuscaPedidoMesIStrategy();
        
        Resultado rs = strategy.processar();
        
        if(!rs.getMensagemSimples().equals(""))
        {
            request.setAttribute("MsgIndex", rs.getMensagemSimples());
        }
        else
        {
            request.setAttribute("pedidos", rs.getEntidades());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
   
}
