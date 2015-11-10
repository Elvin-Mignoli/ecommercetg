/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.AvaliacaoDAO;
import br.com.ecommerce.domain.Avaliacao;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteAvaliacaoDAO
{
    public static void main(String[] args) throws SQLException
    {
        Pedido pedido = new Pedido();
        
        pedido.setId(6);
        pedido.setCliente(new Cliente());
        pedido.getCliente().setId(10);
        pedido.setPrestadorFinalista(new PrestadorServico());
        pedido.getPrestadorFinalista().setId(28);
        pedido.setAvaliacao(new Avaliacao());
        pedido.getAvaliacao().setData(Calendar.getInstance());
        pedido.getAvaliacao().setDescricao("Tudo foi ótimo!");
        pedido.getAvaliacao().setPontos(3.5);
        
        IDAO dao = new AvaliacaoDAO();
        
        dao.salvar(pedido);
    }
}