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
import br.com.ecommerce.domain.Pedido;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarPedidoClienteVHWeb implements IViewHelper
{

    private Pedido pedido;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        try
        {
            pedido = new Pedido();
            
            Integer id = new Integer(request.getParameter("txtId"));
            String descricao = request.getParameter("txtDescricao").trim();
            String habilidadePrestador[] = request.getParameter("txtHabilidadeRequirida").split(",");
            String minhaHabilidade[] = request.getParameter("txtMinhaHabilidade").split(",");
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = format.parse(request.getParameter("txtDataInicio"));
            Date dataFim = format.parse(request.getParameter("txtDataTermino"));
            format = new SimpleDateFormat("HH:mm");
            Calendar hora = Calendar.getInstance();
            hora.setTime(format.parse(request.getParameter("txtHora")));
            
            Cliente cliente = (Cliente) request.getSession().getAttribute("user");
            
            pedido.setCliente(cliente);
            pedido.setId(id);
            pedido.setDescricao(descricao);
            pedido.setDataInicio(dataInicio);
            pedido.setDataFim(dataFim);
            pedido.setHoraConsultoria(hora);
            pedido.addHabilidadeRequerida(habilidadePrestador);
            pedido.addHabilidadeCliente(minhaHabilidade);
            
            return pedido;
        } 
        catch (ParseException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(!resultado.getMensagens().isEmpty()) //A lista de mensagens não é vazia??
        {
            request.setAttribute("MsgAtualiza", resultado.getMensagens());
        }
        else
        {
            request.setAttribute("MsgAtualiza", "Pedido Atualizado com sucesso!");
        }
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }

}
