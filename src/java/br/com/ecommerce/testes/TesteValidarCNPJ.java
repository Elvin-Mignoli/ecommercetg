/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.impl.IStrategy.ValidaCNPJ;
import br.com.ecommerce.domain.PrestadorServico;

/**
 *
 * @author Elvin
 */
public class TesteValidarCNPJ {
    public static void main(String[] args) {
        
        PrestadorServico prestador = new PrestadorServico();
        
        prestador.setCnpj("00.776.574/0001-56");
        
        Resultado res = new Resultado();
        res = new ValidaCNPJ().processar(prestador);
        System.out.println(res.getMensagemSimples());
    }
}
