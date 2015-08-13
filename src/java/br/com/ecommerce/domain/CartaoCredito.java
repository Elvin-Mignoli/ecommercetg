/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Felipe Monteiro
 */
public class CartaoCredito extends EntidadeDominio
{
    private String numeroCartao;
    private String numeroSeguranca;
    private String bandeira;
    private String nomeTitular;
    private Calendar validade;
    
    public CartaoCredito()
    {
        numeroCartao = "";
        numeroSeguranca = "";
        bandeira = "";
        nomeTitular = "";
        validade = null;
    }
    
    public String getNumeroCartao()
    {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao)
    {
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroSeguranca()
    {
        return numeroSeguranca;
    }

    public void setNumeroSeguranca(String numeroSeguranca)
    {
        this.numeroSeguranca = numeroSeguranca;
    }

    public String getBandeira()
    {
        return bandeira;
    }

    public void setBandeira(String bandeira)
    {
        this.bandeira = bandeira;
    }

    public String getNomeTitular()
    {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular)
    {
        this.nomeTitular = nomeTitular;
    }

    public Calendar getValidade()
    {
        return validade;
    }

    public void setValidade(Calendar validade)
    {
        this.validade = validade;
    }
}
