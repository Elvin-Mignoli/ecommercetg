/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.NotifyDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Notify;
import br.com.ecommerce.domain.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class TesteNotify {
    public static void main(String[] args) throws SQLException {
        
       /*Pedido pedido = new Pedido();
       pedido.setId(27);
       
       Notify notify = new Notify(7, 10, pedido, 25);
       notify.setFlgOn(true);
       
       NotifyDAO dao = new NotifyDAO();
       dao.salvar(notify);*/
        
       /*Pedido pedido = new Pedido();
       pedido.setId(27);
       
       Notify notify = new Notify(7, 10, pedido, 25);
       notify.setFlgOn(true);
       
       NotifyDAO dao = new NotifyDAO();
       dao.atualizar(notify);*/
        
       /*Pedido pedido = new Pedido();
       pedido.setId(27);
       
       Notify notify = new Notify(7, 10, pedido, 25);
       notify.setFlgOn_cliente(true);
       
       NotifyDAO dao = new NotifyDAO();
       List<EntidadeDominio> lista = new ArrayList<>();
       lista =  dao.consultar(notify);
       
       for(EntidadeDominio e:lista)
       {
           Notify nt = (Notify) e;
           System.out.println("Canal: " +nt.getChannelChat());
           System.out.println("Cliente: " +nt.getIdCliente());
           System.out.println("Prestador: " +nt.getIdPrestador());
           System.out.println("Pedido: " +nt.getPedido().getId());
           System.out.println("Status: " +nt.isFlgOn_cliente());
       }*/
       
        String teste = "oi";
        String teste2 = "tudo bem";
        String lista = "";
        lista = lista + teste + ",";
         lista = lista +teste2 + ",";
         System.out.println(lista);
    }
}
