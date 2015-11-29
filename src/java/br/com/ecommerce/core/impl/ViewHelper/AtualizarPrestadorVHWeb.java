/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class AtualizarPrestadorVHWeb implements IViewHelper{

    PrestadorServico prestador;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        //tratamento da string de competencias
        PrestadorServico sessionPrestador = (PrestadorServico) request.getSession().getAttribute("user");
        prestador = sessionPrestador;
        ArrayList<Competencia> listComp = new ArrayList<>();
        String[] listSkill = request.getParameter("txtSkill").split(",");
        int contador = listSkill.length;
         for(String skill: listSkill)
            {
                if(skill.equals(""))
                    continue;
                skill = skill.trim();
                skill= skill.toUpperCase();
                if(contador >1){
                    skill= skill.concat(",");
                    contador--;
                }
                Competencia comp = new Competencia();
                comp.setDescricao(skill);
                listComp.add(comp);
               

             }
        if(!listComp.isEmpty())
        {
            prestador.setHabilidades(listComp);
            sessionPrestador.setHabilidades(listComp);
        }
        else
            prestador.setHabilidades(sessionPrestador.getHabilidades());
        /* Dados do Pessoais do Prestador de servico */
        String nome = request.getParameter("txtNome");
        String sobrenome = request.getParameter("txtSobrenome");
        String cpf = request.getParameter("txtCpf");
         String cnpj = request.getParameter("txtCnpj");
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
        endereco.setId(sessionPrestador.getEndereco().getId());
        
        /* Classe de Contato! */
        String telefone = request.getParameter("txtTelefone").replace("(", "").replace(")", "").replace("-", "");
        String celular = request.getParameter("txtCelular").replace("(", "").replace(")", "").replace("-", "");
        Contato contato = new Contato(telefone, celular);

        prestador.setId(sessionPrestador.getId());
        prestador.setNome(nome);
        prestador.setSobrenome(sobrenome);
        prestador.setCpf(cpf);
        prestador.setCnpj(cnpj);
        prestador.setSexo(sexo);
        prestador.setDataNascimento(data);
        prestador.setContato(contato);
        prestador.setEndereco(endereco);

        return prestador;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        
         if (!resultado.getMensagens().isEmpty())    //a lista de mensagens de erro esta vazia
        {
            //request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens
            out.print(resultado.getMensagens().toString());
        }
        else
        {
            //request.setAttribute("MsgAtualiza", "Dados atualizados com sucesso!");
            request.getSession().setAttribute("user", prestador);
            out.print("");
            
        }
        //request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
    }
    
}
