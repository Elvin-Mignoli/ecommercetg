/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class Interessado extends EntidadeDominio
{
    private Pedido pedido;
    private List<PrestadorServico> prestadores;

    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public List<PrestadorServico> getPrestadores()
    {
        return prestadores;
    }

    public void setPrestadores(List<PrestadorServico> prestadores)
    {
        this.prestadores = prestadores;
    }
    
    
}
