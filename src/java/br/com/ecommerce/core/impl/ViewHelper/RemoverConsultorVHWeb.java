/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.RemoverConsultorIStrategy;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class RemoverConsultorVHWeb implements IViewHelper
{

    private Pedido pedido;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        pedido = new Pedido();
        pedido.setCliente(new Cliente());
        pedido.setPrestadorFinalista(new PrestadorServico());

        pedido.getCliente().setId(new Integer(request.getParameter("txtIdCliente")));
        pedido.getPrestadorFinalista().setId(new Integer(request.getParameter("txtIdPrestador")));
        pedido.setId(new Integer(request.getParameter("txtIdPedido")));

        pedido.setStatus(Status.NAO_SELECIONADO);

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        pedido = (Pedido) getEntidade(request);

        IStrategy strategy = new RemoverConsultorIStrategy();

        Resultado rs = strategy.processar(pedido);

        if (rs.getMensagemSimples() != null) //contem mensagens de erro??
        {
            request.setAttribute("MsgErro", rs.getMensagemSimples());
        }
        request.getRequestDispatcher("ClienteDetalhePedido.jsp").forward(request, response);
    }

}
