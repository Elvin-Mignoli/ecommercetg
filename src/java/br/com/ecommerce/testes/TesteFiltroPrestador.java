/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteFiltroPrestador
{
    public static void main(String[] args) throws SQLException
    {
        PrestadorServico pr = new PrestadorServico();
        pr.setId(10);
        
        Pedido pe = new Pedido();
        
        pe.setPrestadorFinalista(pr);
        
        PedidoDAO dao = new PedidoDAO();
        
        pe.setConsulta(Pedido.PRESTADOR);
        
        List<EntidadeDominio> entidades = dao.consultar(pe);
        
        for(EntidadeDominio ed : entidades)
        {
            pe = (Pedido) ed;
            
            System.out.println(pe.getDescricao());
        }
    }
}
