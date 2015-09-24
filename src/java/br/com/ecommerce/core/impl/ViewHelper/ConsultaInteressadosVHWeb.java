/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ConsultarInscritosIStrategy;
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
public class ConsultaInteressadosVHWeb implements IViewHelper
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
        
        IStrategy strategy = new ConsultarInscritosIStrategy();  //executa o strategy
        
        Resultado rs = strategy.processar(pedido);  //retorna o resultado do Strategy
        
        if(rs.getMensagemSimples() != null) //mensagem é nula??
        {
            request.setAttribute("MsgAtualiza", rs.getMensagemSimples());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("pedido", rs.getEntidade());
            request.getRequestDispatcher("ClienteInscritos.jsp").forward(request, response);
        }
        
    }
    
}
