/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class SalvarClienteVHWeb implements IViewHelper
{
    private Cliente cliente;
    
    public SalvarClienteVHWeb()
    {
    }
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        /* Dados do Pessoais do Cliente */
        String nome = request.getParameter("txtNome");
        String sobrenome = request.getParameter("txtSobrenome");
        String cpf = request.getParameter("txtCpf");
        String email = request.getParameter("txtLogin");
        String senha = request.getParameter("txtSenha");

        cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setTipoConta("cliente");
        cliente.setStatus(1);
        cliente.setEndereco(new Endereco());
        
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;   //dispatcher!

        if (resultado.getMensagens().isEmpty())  //lista de mensagens esta vazia?
        {
            request.setAttribute("MsgSucess", "Dados salvos com sucesso!");
            dispatcher = request.getRequestDispatcher("ClienteSalvar.jsp");
        } else
        {
            //setando as mensagens de erro para o cliente!
            request.setAttribute("MsgSalvarCliente", resultado.getMensagens());
            request.setAttribute("cliente", cliente);
            dispatcher = request.getRequestDispatcher("ClienteSalvar.jsp");
        }

        dispatcher.forward(request, response);  //manda a requisicao de volta!
    }
}
