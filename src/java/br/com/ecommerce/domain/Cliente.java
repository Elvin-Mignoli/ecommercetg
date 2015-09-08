/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
//imports do programa

public class Cliente extends Usuario
{
    private CartaoCredito cartao;
    private List<EntidadeDominio> pedidos = new ArrayList<>();
    
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
    
    /**
     * Retorna a quantidade de Pedidos de um determinado cliente
     * @return retorna uma inteira contendo a quantidade de Clientes
     */
    public Integer qtdePedidos()
    {
        return pedidos.size();
    }
    
    /**
     * Adiciona pedidos a lista de pedidos de um cliente
     * @param entidade Classe Pedido (Subclasse de EntidadeDominio)
     */
    public void addPedidos(EntidadeDominio entidade)
    {
        pedidos.add(entidade);
    }
    
    /**
     * Avisa se a lista de pedidos de um cliente esta vazia ou nao
     * @return true se a lista de pedidos estiver vazia
     *         false se a lista contem 1 ou n pedidos
     */
    public boolean isPedidos()
    {
      return pedidos.isEmpty();
    }
    
    public void setPedidos(List<EntidadeDominio> entidades)
    {
        this.pedidos = entidades;
    }
}
