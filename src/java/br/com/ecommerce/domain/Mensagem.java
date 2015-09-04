/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

/**
 *
 * @author Felipe Monteiro
 */
public class Mensagem extends EntidadeDominio
{
    private String descricao;
    private String destinatario;
    private String remetente;

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
