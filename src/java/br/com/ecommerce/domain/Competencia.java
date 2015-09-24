/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.security.spec.ECFieldFp;

/**
 *
 * @author Elvin
 */
public class Competencia extends EntidadeDominio{
    
    private String descricao;  //a descrição da competencia que o usuário do tipo prestador de serviço possui. ex: Java Web.

    public Competencia(String descricao) {
        this.descricao = descricao;
    }

    public Competencia() {
    }

    
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString(){
        return descricao;
    }
}
