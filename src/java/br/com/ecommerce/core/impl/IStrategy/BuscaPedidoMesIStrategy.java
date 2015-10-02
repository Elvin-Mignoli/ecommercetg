/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Monteiro
 */ 
public class BuscaPedidoMesIStrategy implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Resultado processar()
    {
        PedidoDAO dao = new PedidoDAO();
        Resultado rs = new Resultado();
        try
        {
            rs = dao.totalPedidosMes();
            return rs;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.setMensagemSimples("Erro ao tentar criar gráfico!");
            return null;
        }        
    }    
}
