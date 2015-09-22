/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteConsultaPedidos
{
    public static void main(String[] args) throws SQLException
    {
        Cliente cliente = new Cliente();
        cliente.setId(4);
        
        Pedido pedido = new Pedido();
        
        pedido.setCliente(cliente);
        
        IDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> entidades = dao.consultar(pedido);
        
        String value = entidades.isEmpty() ? "Vazio" : entidades.size()+"";
        
        System.out.println("Value: "+value);
        
        for(EntidadeDominio ent : entidades)
        {
            Pedido pe = (Pedido) ent;
            
            System.out.println(pe.getDescricao());
        }
    }
}
