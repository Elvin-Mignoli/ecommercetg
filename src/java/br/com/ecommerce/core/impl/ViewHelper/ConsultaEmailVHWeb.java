/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ExisteEmail;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class ConsultaEmailVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        if (email.contains("@") && email.contains(".") && email.contains("com"))
        {
            Usuario usuaria = new Usuario();
            usuaria.setEmail(email);

            IStrategy business = new ExisteEmail();
            //decidino qual CPF buscar Cliente ou Prestador
           
                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                Resultado rs = business.processar(usuario);

                if (rs.getMensagemSimples() != null)
                
                    out.print("Já existe alguém com esse Email!");
                }
                else
                {{
                    out.print("Email Inválido!");
                }
           
        }//if
    }
}
