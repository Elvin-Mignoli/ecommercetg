/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.BuscaPedidoMes;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;

/**
 *
 * @author java
 */
public class TesteBuscaPedidoMes
{
    public static void main(String[] args) throws SQLException
    {
        PedidoDAO dao = new PedidoDAO();
        
        Resultado rs = dao.totalPedidosMes();
        
        System.out.println(rs.getEntidades().size());
        
        for (EntidadeDominio ed : rs.getEntidades())
        {
            BuscaPedidoMes bpm = (BuscaPedidoMes) ed;
        }
    }
}
