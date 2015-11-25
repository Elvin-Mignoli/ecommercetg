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
import br.com.ecommerce.domain.Notify;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class VideoChatVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //pegar atributos da jsp
        Notify notify = new Notify();
        notify.setChannelChat(Integer.parseInt(request.getParameter("Canal")));
        notify.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        notify.setIdPrestador(Integer.parseInt(request.getParameter("idPrestador")));
        Pedido pedido = new Pedido();
        pedido.setId(Integer.parseInt(request.getParameter("idPedido")));
        notify.setPedido(pedido);
        
        request.setAttribute("notify", notify);
        
        if(request.getSession().getAttribute("user") instanceof Cliente){
            request.getRequestDispatcher("ClienteVideoConferencia.jsp?canal="+notify.getChannelChat()).forward(request, response); 
        }
        if(request.getSession().getAttribute("user") instanceof PrestadorServico){
             request.getRequestDispatcher("PrestadorVideoConferencia.jsp?canal="+notify.getChannelChat()).forward(request, response);
        }
       
    }
    
}
