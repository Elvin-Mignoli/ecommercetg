/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class SalvarPrestadorVHWeb implements IViewHelper{

     PrestadorServico prestador;
        
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        /* Dados do Pessoais do Prestador de serviço */
        prestador = new PrestadorServico();
        
        prestador.setNome(request.getParameter("txtNome"));
        prestador.setSobrenome(request.getParameter("txtSobrenome"));
        if(!request.getParameter("txtCpf").equals(""))
            prestador.setCpf(request.getParameter("txtCpf"));
        if(!request.getParameter("txtCnpj").equals(""))
            prestador.setCnpj(request.getParameter("txtCnpj")); 
        prestador.setEmail(request.getParameter("txtLogin"));
        prestador.setSenha(request.getParameter("txtSenha"));
        prestador.setTipoConta("Prestador");
        prestador.setStatus(1);
        prestador.setEndereco(new Endereco());
        return prestador;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher dispatcher;   //dispatcher!

        if (resultado.getMensagens().isEmpty())  //lista de mensagens esta vazia?
        {
            request.setAttribute("MsgSucess", "Dados salvos com sucesso!");
            dispatcher = request.getRequestDispatcher("PrestadorSalvar.jsp");
        } else
        {
            //setando as mensagens de erro para o cliente!
            request.setAttribute("MsgSalvarCliente", resultado.getMensagens());
            request.setAttribute("prestador", prestador);
            dispatcher = request.getRequestDispatcher("PrestadorSalvar.jsp");
        }

        dispatcher.forward(request, response);  //manda a requisicao de volta!
    }
    
}
