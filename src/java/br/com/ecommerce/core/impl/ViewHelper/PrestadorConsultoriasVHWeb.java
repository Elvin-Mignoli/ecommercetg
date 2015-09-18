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
import static br.com.ecommerce.domain.Pedido.PRESTADOR;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class PrestadorConsultoriasVHWeb implements IViewHelper{
    Pedido pedido = new Pedido();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        pedido.setPrestadorFinalista((PrestadorServico)request.getSession().getAttribute("user"));
        pedido.setConsulta(PRESTADOR);
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       pedido.setPedidos(resultado.getEntidades());
       if(pedido.getPedidos().isEmpty())
           pedido.setPedidos(null);
       if(resultado.getMensagens()!=null){
            request.setAttribute("ListaPedido", pedido);
            request.getRequestDispatcher("PrestadorConsultorias.jsp").forward(request, response);
           
        }else{
            request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
        }
    }
    
}
