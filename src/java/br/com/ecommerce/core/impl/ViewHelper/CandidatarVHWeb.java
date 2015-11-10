/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.Candidatar;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class CandidatarVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrestadorServico usuario = new PrestadorServico();
        usuario.setId(Integer.parseInt(request.getParameter("id_prestador")));
        usuario.setPedido(new Pedido());
        usuario.getPedido().setId(Integer.parseInt(request.getParameter("id_pedido")));
        usuario.setValorConsultori(Integer.parseInt(request.getParameter("valor")));
        Candidatar candidar = new Candidatar();
        resultado = candidar.processar(usuario);
        
        if(resultado.getMensagens().isEmpty()){
             response.getWriter().write("");
        }else{
            response.getWriter().write(resultado.getMensagens().toString());
        }
        // request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
    }
    
}
