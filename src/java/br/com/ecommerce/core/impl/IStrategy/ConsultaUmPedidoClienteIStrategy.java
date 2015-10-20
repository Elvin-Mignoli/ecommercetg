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

/**
 *
 * @author java
 */
public class ConsultaUmPedidoClienteIStrategy implements IStrategy
{
    private Resultado rs;
    
    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        PedidoDAO dao = new PedidoDAO();
        
        rs = new Resultado();
        
        EntidadeDominio pedido;
        
        try
        {
            pedido = dao.consultarUm(entidade);
            
            rs.setEntidade(pedido);
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.setMensagemSimples("Erro ao buscar pedido, tente novamente mais tarde!");
        }
        return rs;
    }
    
}
