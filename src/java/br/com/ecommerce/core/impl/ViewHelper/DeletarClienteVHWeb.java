/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class DeletarClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //criando dispacher
        RequestDispatcher rd;

        if (!resultado.getMensagens().isEmpty()) //a lista de mensagens nao eh vazia?
        {
            request.setAttribute("MsgDeletarCliente", resultado.getMensagens()); //retorna lista de mensagens!
        }
        rd = request.getRequestDispatcher("ClienteDeletar.jsp");

        //retornando a requisicao!
        rd.forward(request, response);
    }

}
