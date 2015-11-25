/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;

/**
 *
 * @author Elvin
 */
public class IdentificarDocHeadHunter implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        HeadHunter head =  (HeadHunter) entidade;
        Resultado resultado;
        if(head.getCpf() == null)
        {
            resultado = new ValidaCNPJ().processar(head);
            return resultado;
        }else
        {
            resultado = new ValidaCPF().processar(head);
            return resultado;
        }
    }
    
}
