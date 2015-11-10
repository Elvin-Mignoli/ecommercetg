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
public class TestePedidoDAO
{
    public static void main(String[] args)
    {
        IDAO dao = new PedidoDAO();
        Pedido pedido = new Pedido();
        
        pedido.setDescricao("Ajax com Java para paginas web");
        pedido.addHabilidadeRequerida("ajax","java","Servelt");
        pedido.addHabilidadeCliente("java","servlet");
        pedido.setData(Calendar.getInstance());
        
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.DAY_OF_MONTH, 20);
        dataInicio.set(Calendar.MONTH, Calendar.AUGUST);
        dataInicio.set(Calendar.YEAR, 2015);
        
        pedido.setDataInicio(dataInicio.getTime());
        
        Calendar dataFim = Calendar.getInstance();
        dataFim.set(Calendar.DAY_OF_MONTH, 31);
        dataFim.set(Calendar.MONTH, Calendar.AUGUST);
        dataFim.set(Calendar.YEAR, 2015);
        
        pedido.setDataFim(dataFim.getTime());
        
        pedido.setStatus(Status.ABERTO);
        pedido.getData().get(Calendar.MONTH);
        Cliente cliente = new Cliente();
        cliente.setId(4);
        pedido.setCliente(cliente);
        
        try
        {
           
           dao.salvar(pedido);
        }
        catch (SQLException ex)
        {
            System.out.println("erro ao salvar os dados!");
        }
    }
}
