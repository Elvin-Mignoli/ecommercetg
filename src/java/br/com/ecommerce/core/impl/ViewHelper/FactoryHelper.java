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
    private static Map<String,IViewHelper> helpers = new HashMap<>();
    
    //esse bloco de codigo carrega quando a classe é carregada!
    static
    {
        //insere a URL e o Helper ligado à essa URL
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
