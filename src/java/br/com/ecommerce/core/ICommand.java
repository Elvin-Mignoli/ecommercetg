/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.impl.controller.Fachada;
import br.com.ecommerce.domain.EntidadeDominio;

/**
 *
 * @author Felipe Monteiro
 * Interface para encapsular os métodos da fachada!
 */
public interface ICommand
{
    //para retornar os dados vindos da Fachada que só retorna Resultado
    public Resultado execute(EntidadeDominio entidade);
}
