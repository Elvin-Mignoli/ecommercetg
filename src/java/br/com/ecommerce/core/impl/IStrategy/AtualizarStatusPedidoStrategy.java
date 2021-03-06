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
 * @author Felipe Monteiro
 */
public class AtualizarStatusPedidoStrategy implements IStrategy
{
    private Resultado rs;
    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        rs = new Resultado();
        
        PedidoDAO dao = new PedidoDAO();
        
        try
        {
            dao.SelecionarPrestador(entidade);
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.setMensagemSimples("Erro ao Status do Pedido");
        }
        return rs;
    }
    
}
