/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;

/**
 *
 * @author Elvin
 */
public class IdentificarDocumento implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        
        PrestadorServico prestador =  (PrestadorServico) entidade;
        Resultado resultado;
        if(prestador.getCpf() == null)
        {
            resultado = new ValidaCNPJ().processar(prestador);
            return resultado;
        }else
        {
            resultado = new ValidaCPF().processar(prestador);
            return resultado;
        }
    }
    
}
