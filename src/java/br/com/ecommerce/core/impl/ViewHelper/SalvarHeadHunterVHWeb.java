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
import br.com.ecommerce.domain.HeadHunter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class SalvarHeadHunterVHWeb implements IViewHelper{

    HeadHunter head;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        /* Dados do Pessoais do Prestador de serviço */
        head = new HeadHunter();
        
        head.setNome(request.getParameter("txtNome"));
        head.setSobrenome(request.getParameter("txtSobrenome"));
        if(!request.getParameter("txtCpf").equals(""))
            head.setCpf(request.getParameter("txtCpf"));
        if(!request.getParameter("txtCnpj").equals(""))
            head.setCnpj(request.getParameter("txtCnpj")); 
        head.setEmail(request.getParameter("txtLogin"));
        head.setSenha(request.getParameter("txtSenha"));
        head.setTipoConta("HeadHunter");
        head.setStatus(1);
        head.setEndereco(new Endereco());
        head.setNome_empresa(request.getParameter("txtNomeEmpresa"));
        return head;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       RequestDispatcher dispatcher;   //dispatcher!

        if (resultado.getMensagens().isEmpty())  //lista de mensagens esta vazia?
        {
            request.setAttribute("MsgSucess", "Dados salvos com sucesso!");
            dispatcher = request.getRequestDispatcher("HeadHunterSalvar.jsp");
        } else
        {
            //setando as mensagens de erro para o cliente!
            request.setAttribute("MsgSalvarCliente", resultado.getMensagens());
            request.setAttribute("head", head);
            dispatcher = request.getRequestDispatcher("HeadHunterSalvar.jsp");
        }

        dispatcher.forward(request, response);  //manda a requisicao de volta!
    }
    
}
