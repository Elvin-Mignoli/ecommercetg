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
import br.com.ecommerce.domain.Status;
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
 * @author admin
 */
public class SalvarPedidoVHWeb implements IViewHelper
{

    private Pedido pedido;
    private Cliente cliente;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.pedido = new Pedido();
        cliente = (Cliente) request.getSession().getAttribute("user");

        //atributos da pagina HTML
        String descricao = request.getParameter("txtDescricao");
        String requiredHability[] = request.getParameter("txtHabilidadeRequirida").split(","); //habilidade requirida
        String clienteHability[] = request.getParameter("txtMinhaHabilidade").split(",");     //habilidade do cliente

        //Pegando as Datas de Inicio e Fim
        Date dataInicio = null;
        Date dataFim = null;
        Calendar horaPedido = Calendar.getInstance();
        try
        {
            dataInicio = format.parse(request.getParameter("txtDataInicio"));
            dataFim = format.parse(request.getParameter("txtDataTermino"));

            //pegando hora do pedido
            format = new SimpleDateFormat("HH:mm");
            String hora = request.getParameter("txtHora");
            
            horaPedido.setTime(format.parse(hora));
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        //colocando os atributos no pedido
        pedido.setCliente(cliente);
        pedido.setDescricao(descricao);
        pedido.setDataInicio(dataInicio);
        pedido.setDataFim(dataFim);
        pedido.setHoraConsultoria(horaPedido);
        pedido.setStatus(Status.ABERTO);
        pedido.setData(Calendar.getInstance()); //pegando a data que foi feito o pedido!

        //pegando halidades requiridas
        for (String r : requiredHability)
        {
            pedido.addHabilidadeRequerida(r);
        }

        //pegando habilidades do cliente
        for (String c : clienteHability)
        {
            pedido.addHabilidadeCliente(c);
        }

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (resultado.getMensagens().isEmpty())
        {
            cliente.addPedidos(pedido);
            request.getSession().setAttribute("user", cliente);
            request.setAttribute("MsgAtualiza", "Pedido efetuado com Sucesso!");
        }
        else
        {
            request.getSession().setAttribute("user", cliente);
            request.setAttribute("MsgAtualiza", resultado.getMensagens());
        }

        //Mandando a requisicao para a pagina principal
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }

}
