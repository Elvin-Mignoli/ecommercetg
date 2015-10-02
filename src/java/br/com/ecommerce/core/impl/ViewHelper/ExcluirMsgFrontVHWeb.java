/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class ExcluirMsgFrontVHWeb implements IViewHelper{

    Usuario usuario = new Usuario();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String local = request.getParameter("local");
        //boolean flag = false; // flag para identificar se foi exlcuido uma mensagem na arraylist
        usuario = (Usuario)request.getSession().getAttribute("user");
        Mensagem msg = new Mensagem();
        msg.setId(Integer.parseInt(request.getParameter("txtId")));

        for(EntidadeDominio mensagens: usuario.getEntrada().getMensagens())
        {
            Mensagem m = (Mensagem) mensagens;
            if(m.getId() == msg.getId())
            {
                msg = m;//pegas os valores dos atributos com base no id
                if(local.equals("enviadas"))
                {
                   msg.setFlg_excluida_enviada(true);
                }else if(local.equals("entrada"))
                {
                   msg.setFlg_excluida_recebido(true); 
                }
            }
        }
        usuario.getEntrada().setMensagem(msg);
        return usuario.getEntrada();
        
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Para JQuery Function
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
       
        if(!resultado.getMensagens().isEmpty())
        {
           out.print("Ocorreu algum erro na exclusão da mensagem tente mais tarde!"); //Para JQuery Function
        }
        else
        {
            //decidino se é Cliente ou Prestador
            if (request.getRequestURI().contains("Cliente"))
            {
                out.print("Mensagem excluida com sucesso!"); //Para JQuery Function
                request.getSession().setAttribute("user", (Cliente)usuario);
            }else if (request.getRequestURI().contains("Prestador"))
            {
                out.print("Mensagem excluida com sucesso!"); //Para JQuery Function
                request.getSession().setAttribute("user", (PrestadorServico)usuario);
            }
        }  
    }
    
}
