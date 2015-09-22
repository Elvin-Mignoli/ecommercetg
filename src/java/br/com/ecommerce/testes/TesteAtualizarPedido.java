/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.Status;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteAtualizarPedido
{
    public static void main(String[] args)
    {
        IDAO dao = new PedidoDAO();
        Pedido pedido = new Pedido();
        
        pedido.setId(1);
        pedido.setDescricao("Minha primeira atualização");
        pedido.addHabilidadeRequerida("Ajax","Java","Servelt");
        pedido.addHabilidadeCliente("Java","Servlet");
        pedido.setData(Calendar.getInstance());
        
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.DAY_OF_MONTH, 26);
        dataInicio.set(Calendar.MONTH, Calendar.AUGUST);
        dataInicio.set(Calendar.YEAR, 1992);
        
        pedido.setDataInicio(dataInicio.getTime());
        
        Calendar dataFim = Calendar.getInstance();
        dataFim.set(Calendar.DAY_OF_MONTH, 31);
        dataFim.set(Calendar.MONTH, Calendar.AUGUST);
        dataFim.set(Calendar.YEAR, 2014);
        
        pedido.setDataFim(dataFim.getTime());
        
        pedido.getHoraConsultoria().set(Calendar.HOUR, Calendar.HOUR_OF_DAY);
        
        Cliente cliente = new Cliente();
        cliente.setId(4);
        pedido.setCliente(cliente);
        
        try
        {
           
           dao.atualizar(pedido);
        }
        catch (SQLException ex)
        {
            System.out.println("erro ao salvar os dados!");
        }
    }
}
