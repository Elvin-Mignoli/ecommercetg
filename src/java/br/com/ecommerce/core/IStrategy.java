/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core;

import br.com.ecommerce.domain.EntidadeDominio;

/**
 *
 * @author Felipe Monteiro
 * Essa interface é feita para processamento de regras de Negócio
 */
public interface IStrategy
{
    //para processsar a regra de negócio e retornar uma String
    public String processar(EntidadeDominio entidade);
}
