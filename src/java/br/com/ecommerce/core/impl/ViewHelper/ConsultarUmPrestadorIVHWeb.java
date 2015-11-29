/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ConsultarUmPrestador;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.util.json.JSONObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ConsultarUmPrestadorIVHWeb implements IViewHelper
{
    private PrestadorServico prestador;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        prestador = new PrestadorServico();
        prestador.setId(new Integer(request.getParameter("txtIdPrestador")));
        
        return prestador;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        prestador = (PrestadorServico) getEntidade(request);
        
        IStrategy strategy = new ConsultarUmPrestador();
        
        Resultado rs = strategy.processar(prestador);
        
        if(rs.getMensagemSimples() != null)
        {
            response.getWriter().write("ERRO");
        }
        else
        {
            response.setCharacterEncoding("UTF-8");
            JSONObject json = new JSONObject();
            json.put("nome", prestador.getNome());
            json.put("sobrenome", prestador.getSobrenome());
            String habilidades = prestador.getHabilidades().toString().replace("[", "").replace("]", "").replace(",,", ",").trim();
            System.out.println("--> "+habilidades);
            json.put("habilidades", habilidades);
            json.put("celular", prestador.getContato().getCelular());
            System.out.println(json);
            response.getWriter().write(json.toString());
        }
    }
}
