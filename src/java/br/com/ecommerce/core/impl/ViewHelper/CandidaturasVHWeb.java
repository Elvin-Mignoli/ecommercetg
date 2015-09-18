/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.FiltrarConsultoriaPrestador;
import br.com.ecommerce.domain.EntidadeDominio;
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
public class CandidaturasVHWeb implements IViewHelper{

    Pedido pedido =  new Pedido();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
       PrestadorServico session = (PrestadorServico) request.getSession().getAttribute("user");
       PrestadorServico prestador = new PrestadorServico();
       prestador.setId(session.getId());
       pedido.setConsulta(Pedido.MURAL);
       pedido.setPrestadorFinalista(prestador);
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pedido.setPedidos(resultado.getEntidades());
        FiltrarConsultoriaPrestador filtro = new FiltrarConsultoriaPrestador();
        //filtrar os pedidos: somentes os pedidos que o prestador candidatou-se
        resultado = filtro.processar(pedido);
        pedido.setPedidos(resultado.getEntidades());
        pedido.setPrestadorFinalista(null);
        
        if(resultado.getMensagens()!=null){
            request.setAttribute("ListaPedido", pedido);
            request.getRequestDispatcher("PrestadorCandidaturas.jsp").forward(request, response);
           
        }else{
            request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
        }
    
    }
    
}
