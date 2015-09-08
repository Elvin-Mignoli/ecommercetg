/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;

import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.PrestadorServico;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class ExcluirMsgPVHWeb implements IViewHelper{
 
    PrestadorServico prestador = new PrestadorServico();
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        boolean flag = false; // flag para identificar se foi exlcuido uma mensagem na arraylist
        prestador = (PrestadorServico)request.getSession().getAttribute("user");
        Mensagem msg = new Mensagem();
        msg.setId(Integer.parseInt(request.getParameter("txtId")));
        prestador.getEntrada().setMensagem(msg);
        for(EntidadeDominio mensagens: prestador.getEntrada().getMensagens())
        {
            Mensagem m = (Mensagem) mensagens;
            if(m.getId() == msg.getId())
            {
                prestador.getEntrada().getMensagens().remove(m);
                flag = true; // foi exlcuido uma mensagem na arraylyst
            }
            if(flag)//exclui uma mensagem?
                break;
        }
        return prestador.getEntrada();
        
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        if(!resultado.getMensagens().isEmpty())
        {
           request.setAttribute("MsgAtualiza", resultado.getMensagens());   //retorna lista de mensagens 
      
        }else{
           
            request.setAttribute("MsgAtualiza", "Mensagem excluida com sucesso!");
            request.getSession().setAttribute("user", prestador);
           
        }
        request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
        
    }
    
}
