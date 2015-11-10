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
public class Avaliacao extends EntidadeDominio
{
    private String descricao;
    private Double pontos;

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Double getPontos()
    {
        return pontos;
    }

    public void setPontos(Double pontos)
    {
        this.pontos = pontos;
    }
}
