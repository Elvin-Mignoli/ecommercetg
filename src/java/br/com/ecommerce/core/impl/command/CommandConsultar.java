/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.command;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.domain.EntidadeDominio;

/**
 *
 * @author java
 */
public class CommandConsultar extends AbstractICommand
{

    @Override
    public Resultado execute(EntidadeDominio entidade)
    {
        return fachada.consultar(entidade);
    }
    
}
