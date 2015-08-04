/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.util.BuscaCEP;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class ConsultaCEPVHWeb implements IViewHelper
{

    public ConsultaCEPVHWeb()
    {
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String cep = request.getParameter("cep");
        
        String json = BuscaCEP.getEndereco(cep);
        
        System.out.println(json);
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.print(json);
    }
    
}
