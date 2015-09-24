/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.InteressadoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteInteressados
{
    public static void main(String[] args) throws SQLException
    {
        IDAO dao = new InteressadoDAO();
        Pedido p = new Pedido();
        
        p.setId(4);
        
        List<EntidadeDominio> entidades = dao.consultar(p);
        
        for(EntidadeDominio ed : entidades)
        {
            PrestadorServico ps = (PrestadorServico) ed;
            
            System.out.println(ps.getNome());
            System.out.println(ps.getCandidatura());
        }
        
        System.out.println(entidades.size());
    }
}
