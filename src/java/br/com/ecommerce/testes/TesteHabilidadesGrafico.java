/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HabilidadeProcurada;
import java.sql.SQLException;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteHabilidadesGrafico
{
    public static void main(String[] args) throws SQLException
    {
        PedidoDAO dao = new PedidoDAO();
        
        Resultado rs = dao.habilidadesMaisProcuradas();
        
        for(EntidadeDominio ed : rs.getEntidades())
        {
            HabilidadeProcurada hp = (HabilidadeProcurada) ed;
            
            System.out.println("Label: "+hp.getLabel());
            System.out.println("Value: "+hp.getValue());
            System.out.println("-----------------------");
        }
    }
}
