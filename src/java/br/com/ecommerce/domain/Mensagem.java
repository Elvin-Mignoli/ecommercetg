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
public class Mensagem extends EntidadeDominio
{
    private String descricao; //descrição da mensagem
    private String destinatario;//email
    private String remetente;//email
    private int id_caixa_remetente;//id  da caixa de entrada do remetente
    private Date data_msg;//data que foi enviada a mensagem
    private String assunto;// assunto da mensagem
    private int id_caixa_destinatario;//id da caixa de entrada do destinatario
    private boolean flg_resposta; //a mensagem é uma resposta de uma outra mensagem?
    private boolean flg_excluida_enviada;// flag para identificar se a mensagem enviada foi excluida
    private boolean flg_excluida_recebido;//flag para identificar se a mensagem recebida foi excluida
    private boolean flgAberto;
    private int idPedido;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    
    public boolean isFlgAberto()
    {
        return flgAberto;
    }

    public void setFlgAberto(boolean flgAberto)
    {
        this.flgAberto = flgAberto;
    }

    public boolean isFlg_excluida_enviada() {
        return flg_excluida_enviada;
    }

    public void setFlg_excluida_enviada(boolean flg_excluida_enviada) {
        this.flg_excluida_enviada = flg_excluida_enviada;
    }

    public boolean isFlg_excluida_recebido() {
        return flg_excluida_recebido;
    }

    public void setFlg_excluida_recebido(boolean flg_excluida_recebido) {
        this.flg_excluida_recebido = flg_excluida_recebido;
    }
    
    public boolean isFlg_resposta() {
        return flg_resposta;
    }

    public void setFlg_resposta(boolean flg_resposta) {
        this.flg_resposta = flg_resposta;
    }

    public int getId_caixa_destinatario() {
        return id_caixa_destinatario;
    }

    public void setId_caixa_destinatario(int id_caixa_destinatario) {
        this.id_caixa_destinatario = id_caixa_destinatario;
    }
    
    public int getId_caixa_remetente() {
        return id_caixa_remetente;
    }

    public void setId_caixa_remetente(int id_caixa_remetente) {
        this.id_caixa_remetente = id_caixa_remetente;
    }

    
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    
    
    public Date getData_msg() {
        return data_msg;
    }

    public void setData_msg(Date data_msg) {
        this.data_msg = data_msg;
    }
    
   
    
    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDestinatario()
    {
        return destinatario;
    }

    public void setDestinatario(String destinatario)
    {
        this.destinatario = destinatario;
    }

    public String getRemetente()
    {
        return remetente;
    }

    public void setRemetente(String remetente)
    {
        this.remetente = remetente;
    }
    
    
}
