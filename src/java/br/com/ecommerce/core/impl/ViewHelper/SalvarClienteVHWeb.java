/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import java.text.ParseException;
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

    public SalvarClienteVHWeb()
    {
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        /* Dados do Pessoais do Cliente */
        String nome = request.getParameter("txtNome");
        String sobrenome = request.getParameter("txtSobreNome");
        String cpf = request.getParameter("txtCpf");
        String sexo = request.getParameter("txtSexo");
        String tipoConta = request.getParameter("cliente");
        int status = 1; //ativo! --> 0 Inativo!
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String dataNascimento = request.getParameter("txtDataNascimento");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        /* Dados do Endereco do Cliente! */
        String logradouro = request.getParameter("txtLogradouro");
        String numero = request.getParameter("txtNumero");
        String cep = request.getParameter("txtCep");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("txtEstado");
        String complemento = request.getParameter("txtComplemento");

        /* Classe de Contato! */
        String telefone = request.getParameter("txtTelefone");
        String celular = request.getParameter("txtCelular");
        Contato contato = new Contato(telefone, celular);

        Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado, complemento);

        try
        {
            Cliente cliente = new Cliente(nome, sobrenome, cpf, endereco, contato, sexo, dataNascimento, tipoConta, email, senha);
            //Objeto de Cliente Criado com Sucesso!
            return cliente;
        } catch (ParseException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;   //dispatcher!

        if (resultado.getMensagens().isEmpty())  //lista de mensagens esta vazia?
        {
            dispatcher = request.getRequestDispatcher("ClienteSalvar.jsp");
        } else
        {
            //setando as mensagens de erro para o cliente!
            request.setAttribute("MsgSalvarCliente", resultado.getMensagens());
            dispatcher = request.getRequestDispatcher("ClienteSalvar.jsp");
        }
        
        dispatcher.forward(request, response);  //manda a requisicao de volta!
    }
}
