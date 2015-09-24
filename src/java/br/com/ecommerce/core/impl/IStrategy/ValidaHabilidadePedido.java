/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;

/**
 *
 * @author admin
 */
public class ValidaHabilidadePedido implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Pedido pedido = (Pedido) entidade;
        Resultado resultado = new Resultado();
        
        if(pedido.getHabilidadePrestador().isEmpty())
        {
            resultado.addMensagens("Habilidade Requerida não pode estar vazia");
        }
        
        if(pedido.getHabilidadeCliente().isEmpty())
        {
            resultado.addMensagens("Suas Habilidades não pode estar vazia");
        }
        return resultado;
    }
    
}
