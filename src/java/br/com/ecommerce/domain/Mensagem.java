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
    private String descricao;
    private String destinatario;
    private String remetente;
    private int id_caixa_remetente;
    private Date data_msg;
    private String assunto;

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
