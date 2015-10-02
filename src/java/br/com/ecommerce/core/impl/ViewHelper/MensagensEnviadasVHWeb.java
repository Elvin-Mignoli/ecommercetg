/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.MensagensEnviadas;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
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
public class MensagensEnviadasVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        MensagensEnviadas entrada = new MensagensEnviadas();
        Resultado rs;
        rs = entrada.processar(usuario.getEntrada());
        if(rs != null)//conseguiu resgatar as mensagens no banco?
        {//sim
            usuario.getEntrada().setMensagens(rs.getEntidades());
             //decidino se é Cliente ou Prestador
            if (request.getRequestURI().contains("Cliente"))
            {
                request.getSession().setAttribute("user", (Cliente)usuario);
                request.setAttribute("user",(Cliente)usuario);
                request.getRequestDispatcher("ClienteMensagensEnviadas.jsp").forward(request, response);
            }else if (request.getRequestURI().contains("Prestador"))
            {
                
                //request.getSession().setAttribute("user", (PrestadorServico)rs.getEntidade());
                request.setAttribute("user", (PrestadorServico)usuario);
                request.getRequestDispatcher("PrestadorMensagensEnviadas.jsp").forward(request, response);
            }
        }else{//não
              request.setAttribute("MsgAtualiza", "Houve algum erro no servidor, tente novamente mais tarde!");
               if (request.getRequestURI().contains("Cliente"))
                    request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);    
               else if(request.getRequestURI().contains("Prestador"))
                    request.getRequestDispatcher("PrestadorCaixaEntrada.jsp").forward(request, response); 
        }
    }
    
}
