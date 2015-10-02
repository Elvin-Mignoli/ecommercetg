/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.BuscaGraficoInicialIStategy;
import br.com.ecommerce.core.impl.IStrategy.BuscaPedidoMesIStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class BuscaGraficosInicialVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BuscaGraficoInicialIStategy strategy = new BuscaGraficoInicialIStategy();
        
        Resultado rs = strategy.processar();
        
        if(rs == null)
        {
            request.setAttribute("graficoIndex", "Erro ao gerar o gráfico =/");
        }
        else
        {
            request.setAttribute("donnutHabilidade", rs.getEntidades());
        }
        
        BuscaPedidoMesIStrategy busca = new BuscaPedidoMesIStrategy();
        
        rs = busca.processar();
        
        if(rs == null)
        {
            request.setAttribute("graficoIndex", "Erro ao gerar o gráfico =/");
        }
        else
        {
            request.setAttribute("pedidos", rs.getEntidades());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
}
