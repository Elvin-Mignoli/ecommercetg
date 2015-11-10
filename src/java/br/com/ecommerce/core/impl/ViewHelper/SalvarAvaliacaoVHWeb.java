/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.SalvarAvaliacaoIStrategy;
import br.com.ecommerce.domain.Avaliacao;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.util.json.JSONObject;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class SalvarAvaliacaoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Pedido pedido = new Pedido();
        pedido.setId(new Integer(request.getParameter("txtIdPedido")));
        pedido.setCliente(new Cliente());
        pedido.getCliente().setId(new Integer(request.getParameter("txtIdCliente")));
        pedido.setPrestadorFinalista(new PrestadorServico());
        pedido.getPrestadorFinalista().setId(new Integer(request.getParameter("txtIdPrestador")));

        pedido.setAvaliacao(new Avaliacao());
        pedido.getAvaliacao().setData(Calendar.getInstance());
        pedido.getAvaliacao().setDescricao(request.getParameter("txtComentario"));
        pedido.getAvaliacao().setPontos(new Double(request.getParameter("txtNota")));

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        
        IStrategy strategy = new SalvarAvaliacaoIStrategy();

        Resultado rs = strategy.processar(getEntidade(request));

        JSONObject json = new JSONObject();
        
        if (rs.getMensagemSimples() == null)
        {
            json.put("status","success");
            json.put("message", "Avalição efetuada com sucesso");
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
        } else
        {
            json.put("status","success");
            json.put("message", rs.getMensagemSimples());
            response.getWriter().write(json.toString());
        }
    }

}
