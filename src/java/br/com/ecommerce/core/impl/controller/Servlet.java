/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.controller;

import br.com.ecommerce.core.ICommand;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.ViewHelper.FactoryHelper;
import br.com.ecommerce.core.impl.command.FactoryICommand;
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
        //retorna o helper específico da view resultante
        IViewHelper vh = FactoryHelper.getInstance(request.getRequestURI());
        
        //retorna uma entidade
        EntidadeDominio entidade = vh.getEntidade(request);
        
        ICommand command = FactoryICommand.getCommand(request.getParameter("operacao"));
        
        Resultado resultado = command.execute();
        
        vh.setView(resultado, request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        /*
            Executa um doPost por enquanto
            se precisarem utilizar fica critério de vocês
        */
        doPost(req, resp);
    }
}