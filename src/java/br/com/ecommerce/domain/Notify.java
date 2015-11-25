/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

/**
 *
 * @author Elvin
 */
public class Notify extends EntidadeDominio{
    
    private int IdCliente;
    private int idPrestador;
    private Pedido pedido;
    private int channelChat;
    private boolean flgOn_cliente = false;
    private boolean flgOn_prestador = false;
    private String acao;

    public Notify(int IdCliente, int idPrestador, Pedido pedido, int channelChat) {
        this.IdCliente = IdCliente;
        this.idPrestador = idPrestador;
        this.pedido = pedido;
        this.channelChat = channelChat;
    }
    
    public Notify(){}

    public boolean isFlgOn_prestador() {
        return flgOn_prestador;
    }

    public void setFlgOn_prestador(boolean flgOn_prestador) {
        this.flgOn_prestador = flgOn_prestador;
    }

    
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
    
    
    public boolean isFlgOn_cliente() {
        return flgOn_cliente;
    }

    public void setFlgOn_cliente(boolean flgOn_cliente) {
        this.flgOn_cliente = flgOn_cliente;
    }

    
    
    
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getChannelChat() {
        return channelChat;
    }

    public void setChannelChat(int channelChat) {
        this.channelChat = channelChat;
    }
    
    
}
