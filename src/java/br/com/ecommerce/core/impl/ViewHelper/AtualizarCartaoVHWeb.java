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
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarCartaoVHWeb implements IViewHelper
{
    private CartaoCredito cartao = new CartaoCredito();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Cliente cliente = (Cliente) request.getSession().getAttribute("user");
        
        String numeroCartao = request.getParameter("txtNumero");
        String numeroSeguranca = request.getParameter("txtSecureNumber");
        String nomeTitular = request.getParameter("txtTitular");
        String val[] = request.getParameter("txtValidade").split("/");
        int mes = Integer.parseInt(val[0]);
        int ano = Integer.parseInt(val[1]);
        Calendar dt = Calendar.getInstance();
        dt.set(Calendar.MONTH, mes);
        dt.set(Calendar.YEAR,ano);
        String bandeira = request.getParameter("txtBandeira");
        
        cartao.setNomeTitular(nomeTitular);
        cartao.setBandeira(bandeira);
        cartao.setNumeroCartao(numeroCartao);
        cartao.setNumeroSeguranca(numeroSeguranca);
        cartao.setValidade(dt);
        
        cartao.setId(cliente.getCartao().getId());
        
        return cartao;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(resultado.getMensagens().isEmpty())  //não existem mensagens de erro?
        {
            request.setAttribute("MsgAtualiza", "Cartão alterado com sucesso!");
            Cliente cli = (Cliente) request.getSession().getAttribute("user");
            cli.setCartao(cartao);
            request.getSession().setAttribute("user", cli);
        }
        else
        {
            request.setAttribute("MsgAtualiza", resultado.getMensagens());
        }
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
    
}
