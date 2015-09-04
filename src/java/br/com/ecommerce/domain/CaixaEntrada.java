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
public class CaixaEntrada extends EntidadeDominio
{
    private Cliente cliente;
    private PrestadorServico prestador;
    private List<EntidadeDominio> mensagens = new ArrayList<>();
    private Mensagem mensagem;
    
    public CaixaEntrada()
    {
    }

    public CaixaEntrada(Cliente cliente, PrestadorServico servico)
    {
        this.cliente = cliente;
        this.prestador = servico;
    }

    public Mensagem getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem)
    {
        this.mensagem = mensagem;
    }
    
    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public PrestadorServico getPrestador()
    {
        return prestador;
    }

    public void setPrestador(PrestadorServico servico)
    {
        this.prestador = servico;
    }

    public List<EntidadeDominio> getMensagens()
    {
        return mensagens;
    }

    public void setMensagens(List<EntidadeDominio> mensagens)
    {
        this.mensagens = mensagens;
    }
    
}
