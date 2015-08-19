/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.Date;

/**
 *
 * @author Felipe Monteiro
 */
//imports do programa

public class Cliente extends Usuario
{
    private CartaoCredito cartao; 
    
    public Cliente()
    {
    }//default
    
    public Cliente(String cpf)
    {
        super(cpf);
    }

    public CartaoCredito getCartao()
    {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao)
    {
        this.cartao = cartao;
    }
}
