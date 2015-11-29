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
import br.com.ecommerce.domain.Notify;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import br.com.ecommerce.util.json.JSONObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class NotifyConsultarVHWeb implements IViewHelper{
  
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
       Usuario usuario = (Usuario)request.getSession().getAttribute("user");
       Notify notify = new Notify();
       //flags para verificar usuario no ambiente video chat
       if(request.getSession().getAttribute("user") instanceof Cliente)
           notify.setFlgOn_prestador(true);// true - para poder verificar se o prestador esta online no vído chat
       if(request.getSession().getAttribute("user") instanceof PrestadorServico)
           notify.setFlgOn_cliente(true);//true - para poder verificar se o cliente esta online no vído chat
       //setar o id do usuario requisitor da busca
       if(request.getSession().getAttribute("user") instanceof Cliente)
           notify.setIdCliente(usuario.getId());
       if(request.getSession().getAttribute("user") instanceof PrestadorServico)
           notify.setIdPrestador(usuario.getId());
       return notify;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         if(resultado.getMensagens().isEmpty()){
              if(resultado.getEntidades().isEmpty())//não tem notificações
                  response.getWriter().write("Vazio");//não
              
             JSONObject js = new JSONObject();
             for(EntidadeDominio ent: resultado.getEntidades()){
                 Notify notify = (Notify) ent;
                 
                 js.put("pedido",notify.getPedido().getDescricao());
                 js.put("cliente", notify.getPedido().getCliente().getNome() +" " + notify.getPedido().getCliente().getSobrenome());
                 js.put("prestador",notify.getPedido().getPrestadorFinalista().getNome() +" " + notify.getPedido().getPrestadorFinalista().getSobrenome());
                 js.put("canal", notify.getChannelChat());
                 js.put("idCliente", notify.getPedido().getCliente().getId());
                 js.put("idPrestador", notify.getPedido().getPrestadorFinalista().getId());
                 js.put("idPedido", notify.getPedido().getId());
             }
            
             response.getWriter().write(js.toString());
        }else{
            response.getWriter().write(resultado.getMensagens().toString());
        }
    }
    
}
