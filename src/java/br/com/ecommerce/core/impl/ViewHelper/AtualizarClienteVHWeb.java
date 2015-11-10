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
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarClienteVHWeb implements IViewHelper
{

    private Cliente cliente = new Cliente();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Cliente sessionCliente = (Cliente) request.getSession().getAttribute("user");
        /* Dados do Pessoais do Cliente */
        String nome = request.getParameter("txtNome");
        String sobrenome = request.getParameter("txtSobrenome");
        String cpf = request.getParameter("txtCpf");
        String sexo = request.getParameter("txtSexo").substring(0, 1);
        String dataNascimento = request.getParameter("txtDatanascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        
        try
        {
            if(dataNascimento != null || !dataNascimento.equals(""))
            {
                data = sdf.parse(dataNascimento);
            }
        } catch (ParseException ex)
        {
            ex.printStackTrace();
            System.out.println("Data Inválida!");
        }

        /* Dados do Endereco do Cliente! */
        String logradouro = request.getParameter("txtLogradouro");
        String numero = request.getParameter("txtNumero");
        String cep = request.getParameter("txtCep").replace("-", "");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("txtEstado");
        String complemento = request.getParameter("txtComplemento");
        Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado, complemento);
        endereco.setId(sessionCliente.getEndereco().getId());
        
        /* Classe de Contato! */
        String telefone = request.getParameter("txtTelefone").replace("(", "").replace(")", "").replace("-", "");
        String celular = request.getParameter("txtCelular").replace("(", "").replace(")", "").replace("-", "");
        Contato contato = new Contato(telefone, celular);
        
        cliente.setId(sessionCliente.getId());
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setCpf(cpf);
        cliente.setSexo(sexo);
        cliente.setDataNascimento(data);
        cliente.setContato(contato);
        cliente.setEndereco(endereco);
        cliente.setCartao(new CartaoCredito());

        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        if (!resultado.getMensagens().isEmpty())    //a lista de mensagens de erro esta vazia
        {
           // request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            out.print(resultado.getMensagens().toString());
        }
        else
        {
            request.setAttribute("MsgAtualiza", "Dados atualizados com sucesso!");
            request.getSession().setAttribute("user", cliente);
            out.print("");
        }
        //request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
}
