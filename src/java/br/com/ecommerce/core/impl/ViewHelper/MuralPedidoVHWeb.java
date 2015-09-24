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
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class MuralPedidoVHWeb implements IViewHelper{
      Pedido pedido =  new Pedido();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
       pedido.setConsulta(Pedido.MURAL);
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pedido.setPedidos(resultado.getEntidades());
        if(resultado.getMensagens()!=null){
            request.setAttribute("ListaPedido", pedido);
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
           
        }else{
            request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
        }
    }
    
}
