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
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ValidaUsuarioVHWeb implements IViewHelper
{

    public ValidaUsuarioVHWeb()
    {
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtPassword");
        
        Usuario usuario = new Cliente();
        
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(resultado.getEntidade() == null)
        {
            request.setAttribute("loginError", "Login ou Senha inválidos!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else
        {
            request.getSession().setAttribute("user", resultado.getEntidade());
            if(resultado.getEntidade() instanceof Cliente)
            {
                response.sendRedirect("./JSP/Cliente/ClienteDashboard.jsp");
            }
            else if(resultado.getEntidade() instanceof PrestadorServico)
            {
                request.getRequestDispatcher("../../index.jsp");
            }
        }       
    }   
}
