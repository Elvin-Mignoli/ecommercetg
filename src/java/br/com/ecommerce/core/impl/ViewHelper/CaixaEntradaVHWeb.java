/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.OpenCaixaEntrada;
import br.com.ecommerce.domain.CaixaEntrada;
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
public class CaixaEntradaVHWeb implements IViewHelper{

    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
         
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        OpenCaixaEntrada entrada = new OpenCaixaEntrada();
        Resultado rs = new Resultado();
        rs = entrada.processar(usuario);
        if(rs.getEntidade()!=null)
        {
             //decidino se é Cliente ou Prestador
            if (request.getRequestURI().contains("Cliente"))
            {
                request.getSession().setAttribute("user", (Cliente)usuario);
                request.setAttribute("user",(Cliente)usuario);
                request.getRequestDispatcher("").forward(request, response);
            }else if (request.getRequestURI().contains("Prestador"))
            {
                
                request.getSession().setAttribute("user", (PrestadorServico)rs.getEntidade());
                request.setAttribute("user", (PrestadorServico)usuario);
                request.getRequestDispatcher("PrestadorCaixaEntrada.jsp").forward(request, response);
            }
        }
    }
    
}
