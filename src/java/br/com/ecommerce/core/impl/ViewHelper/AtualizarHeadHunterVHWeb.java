/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;
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
 * @author Elvin
 */
public class AtualizarHeadHunterVHWeb implements IViewHelper{

    HeadHunter head;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        HeadHunter session = (HeadHunter) request.getSession().getAttribute("user");
        head = session;
        /* Dados do Pessoais do Cliente */
        String nome = request.getParameter("txtNome");
        String sobrenome = request.getParameter("txtSobrenome");
        String empresa = request.getParameter("txtEmpresa");
        String cpf = request.getParameter("txtCpf");
        String sexo = request.getParameter("txtSexo").substring(0, 1);
        String dataNascimento = request.getParameter("txtDatanascimento");
        dataNascimento = dataNascimento == null ? "":dataNascimento;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        
        try
        {
            if(!dataNascimento.equals(""))
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
        endereco.setId(session.getEndereco().getId());
        
        /* Classe de Contato! */
        String telefone = request.getParameter("txtTelefone").replace("(", "").replace(")", "").replace("-", "");
        String celular = request.getParameter("txtCelular").replace("(", "").replace(")", "").replace("-", "");
        Contato contato = new Contato(telefone, celular);
        
        head.setId(session.getId());
        head.setNome(nome);
        head.setSobrenome(sobrenome);
        head.setNome_empresa(empresa);
        head.setCpf(cpf);
        head.setSexo(sexo);
        head.setDataNascimento(data);
        head.setContato(contato);
        head.setEndereco(endereco);
        head.setCnpj(request.getParameter("txtCnpj"));

        return head;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        if (!resultado.getMensagens().isEmpty())    //a lista de mensagens de erro esta vazia
        {
            out.print(resultado.getMensagens().toString());
        }
        else
        {
            request.getSession().setAttribute("user", head);
            out.print("");
        }
    }
    
}
