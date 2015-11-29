/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class ConsultarRankingVHWeb implements IViewHelper
{
    PrestadorServico prestador = new PrestadorServico();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return prestador;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(resultado.getMensagens().isEmpty())
        {
            request.setAttribute("prestadores", resultado.getEntidades());
        }else
        {
            request.setAttribute("erro", "Houve um erro no servidor");
        }
        request.getRequestDispatcher("HeadHunterDashboard.jsp").forward(request, response);
    }
    
}
