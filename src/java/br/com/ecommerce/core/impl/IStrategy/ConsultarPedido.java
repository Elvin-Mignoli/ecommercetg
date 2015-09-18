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
import br.com.ecommerce.domain.Pedido;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public class ConsultarPedido implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
       Resultado resultado = new Resultado();
       Pedido  pedido = (Pedido) entidade;
       PedidoDAO dao = new PedidoDAO();
        try {
            resultado.setEntidade(dao.consultarUm(pedido));
            return resultado;
        } catch (SQLException ex) {
            ex.printStackTrace();
           resultado.getMensagens().add("Houve algum erro no servidor, tente novamente mais tarde!!!");
        }
       return resultado;
    }
    
}
