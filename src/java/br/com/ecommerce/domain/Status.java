/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

/**
 *
 * @author admin
 */
public enum Status
{
    ABERTO,FECHADO,EM_ANDAMENTO,NEGOCIACAO,SELECIONADO,NAO_SELECIONADO,CANCELADO,EM_PROCESSO,CONCLUIDO;
    
    private String name;
    
    public String getValue()
    {
        return this.name();
    }
}
