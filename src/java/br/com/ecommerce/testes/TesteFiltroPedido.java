/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author java
 */
public class TesteFiltroPedido
{
    public static void main(String[] args) throws SQLException
    {
        Pedido p = new Pedido();
        p.setCliente(new Cliente());
        p.getCliente().setId(4);
        
        p.setConsulta(Pedido.CLIENTE);
        
        PedidoDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> entidade = dao.consultar(p);
        
        for (EntidadeDominio ed : entidade)
        {
            p = (Pedido ) ed;
            
            System.out.println(p.getDescricao());
        }
    }
}
