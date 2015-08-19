/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.CartaoCreditoDAO;
import br.com.ecommerce.domain.CartaoCredito;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteCartao
{
    public static void main(String[] args) throws SQLException
    {
        IDAO dao = new CartaoCreditoDAO();
        CartaoCredito cartao = new CartaoCredito();
        
        cartao.setNomeTitular("Laisla Monteiro");
        cartao.setNumeroCartao("1111");
        cartao.setNumeroSeguranca("111");
        cartao.setValidade(Calendar.getInstance());
        cartao.setBandeira("Master Card");
        cartao.setId(1);
        //dao.salvar(cartao);   --> teste para salvar dados
        //dao.atualizar(cartao);
        CartaoCredito ca = (CartaoCredito) dao.consultarUm(cartao);
        
        System.out.println(ca.getBandeira());
    }
}
