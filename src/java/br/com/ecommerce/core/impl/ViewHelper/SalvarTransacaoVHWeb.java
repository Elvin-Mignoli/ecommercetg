/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.CartaoCredito;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import br.com.ecommerce.domain.Transacao;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class SalvarTransacaoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Transacao tr = new Transacao();
        
        tr.setPedido(new Pedido());
        tr.getPedido().setId(new Integer(request.getParameter("txtIdPedido")));
        tr.getPedido().setStatus(Status.FECHADO);
        tr.setPrestador(new PrestadorServico());
        tr.getPrestador().setId(new Integer(request.getParameter("txtIdPrestador")));
        tr.getPedido().setPrestadorFinalista(tr.getPrestador());
        tr.setCliente(new Cliente());
        tr.getCliente().setId(new Integer(request.getParameter("txtIdCliente")));
        tr.setStatus(Status.FECHADO);
        tr.setTipoPagamento("Cartão de Crédito");
        
        CartaoCredito cartao = new CartaoCredito();
        
        cartao.setNumeroCartao(request.getParameter("txtNumeroCartao"));
        cartao.setNomeTitular(request.getParameter("txtTitular"));
        
        //tratando a data de validade
        String val[] = request.getParameter("txtValidade").split("/");
        int mes = Integer.parseInt(val[0]);
        int ano = Integer.parseInt(val[1]);
        Calendar dt = Calendar.getInstance();
        dt.set(Calendar.MONTH, mes);
        dt.set(Calendar.YEAR,ano);
        //setando a data de validade
        cartao.setValidade(dt);
        
        cartao.setNumeroSeguranca(request.getParameter("txtCodSeguranca"));
        
        cartao.setBandeira(request.getParameter("txtBandeira"));
        
        tr.getCliente().setCartao(cartao);
        
        Double valor = new Double(request.getParameter("txtValor").replaceAll(",", ""));
        
        tr.setValor(valor);
        
        return tr;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(resultado.getMensagens().isEmpty())
        {
               response.getWriter().write("");
        }
        else    //tratando erro!
        {
            response.getWriter().write(resultado.getMensagens().toString());
        }
    }
    
}
