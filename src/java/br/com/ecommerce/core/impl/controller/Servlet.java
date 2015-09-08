/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.controller;

import br.com.ecommerce.core.ICommand;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.ViewHelper.FactoryHelper;
import br.com.ecommerce.core.impl.command.AbstractICommand;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.application.Resultado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 * Servlet única da aplicação
 */
public class Servlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //definir o Enconding
        request.setCharacterEncoding("UTF-8");
        //retorna o helper específico da view resultante
        IViewHelper vh = FactoryHelper.getInstance(request.getRequestURI());
        
        //retorna uma entidade
        EntidadeDominio entidade = vh.getEntidade(request);
        
        ICommand command = AbstractICommand.getCommand(request.getParameter("operacao"));
        
        Resultado resultado = command.execute(entidade);
        
        vh.setView(resultado, request, response);
    }
    
    /**
     * Método utilizado para upload de imagens
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {        
        
    }
}
