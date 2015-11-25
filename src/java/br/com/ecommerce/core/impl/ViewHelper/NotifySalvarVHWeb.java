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
public class NotifySalvarVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
         //pegar atributos da jsp
        Notify notify = new Notify();
        notify.setChannelChat(Integer.parseInt(request.getParameter("Canal")));
        notify.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        notify.setIdPrestador(Integer.parseInt(request.getParameter("idPrestador")));
        Pedido pedido = new Pedido();
        pedido.setId(Integer.parseInt(request.getParameter("idPedido")));
        notify.setPedido(pedido);
        if(request.getSession().getAttribute("user") instanceof Cliente)
        {
            notify.setFlgOn_cliente(true);
            notify.setFlgOn_prestador(false);
        }
        if(request.getSession().getAttribute("user") instanceof PrestadorServico)
        {
            notify.setFlgOn_cliente(false);
            notify.setFlgOn_prestador(true);
        }
        return notify;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        if(resultado.getMensagens().isEmpty()){
             response.getWriter().write("");
        }else{
            response.getWriter().write(resultado.getMensagens().toString());
        }
        
    }
    
}
