/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.Calendar;

/**
 *
 * @author admin
 */
public class Transacao extends EntidadeDominio
{
    private Pedido pedido;
    private PrestadorServico prestador;
    private Calendar dataTransacao;
    private Status status;
    private String tipoPagamento;
    private Cliente cliente;

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public PrestadorServico getPrestador()
    {
        return prestador;
    }

    public void setPrestador(PrestadorServico prestador)
    {
        this.prestador = prestador;
    }

    public Calendar getDataTransacao()
    {
        return dataTransacao;
    }

    public void setDataTransacao(Calendar dataTransacao)
    {
        this.dataTransacao = dataTransacao;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getTipoPagamento()
    {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento)
    {
        this.tipoPagamento = tipoPagamento;
    }
    
    
}
