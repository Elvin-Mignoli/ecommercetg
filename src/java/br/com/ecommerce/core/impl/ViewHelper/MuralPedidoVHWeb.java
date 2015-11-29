/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.AfinidadePedido;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
        
        PrestadorServico prestador = (PrestadorServico) request.getSession().getAttribute("user");
        pedido.setPedidos(resultado.getEntidades());
        prestador.setPedidos((ArrayList<EntidadeDominio>) pedido.getPedidos());
        
        //strategy para calcular a afinidade
        if(prestador.getHabilidades() != null)
        {
            AfinidadePedido afinidade = new AfinidadePedido();
            resultado = afinidade.processar(prestador);
            prestador = (PrestadorServico) resultado.getEntidade();
            pedido.setPedidos(prestador.getPedidos());
        }
        
        if(resultado.getMensagens()!=null){
            request.setAttribute("ListaPedido", pedido);
             //response.getWriter().write("");
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
        }else{
            request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
            //response.getWriter().write(resultado.getMensagens().toString());
        }
    }
    
}
