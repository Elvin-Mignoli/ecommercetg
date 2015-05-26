/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.core.IViewHelper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Monteiro
 * Fábrica para retornar os objetos de um Helper
 */
public abstract class FactoryHelper
{
    //lista Hash para conter todos os helpers
    private final static Map<String,IViewHelper> helpers = new HashMap<>();
    
    //esse bloco de codigo carrega quando a classe é carregada!
    static
    {
        helpers.put("/EcomerceTG/SalvarCliente", new SalvarClienteVHWeb());
        helpers.put("/EcomerceTG/ConsultarUmCliente", new ConsultarUmClienteVHWeb());
        
    }
    
    /**
     * Método para retornar uma implementação de IViewHelper
     * @param URL - URL da requisição
     * @return Retorna uma implementação de IViewHelper
     */
    public static IViewHelper getInstance(String URL)
    {
        return helpers.get(URL);
    }
}
