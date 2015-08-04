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
        helpers.put("/Ecommerce/Cliente/SalvarCliente", new SalvarClienteVHWeb());
        helpers.put("/Ecommerce/Cliente/ConsultarUmCliente", new ConsultarUmClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/SalvarCliente", new SalvarClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/CPF", new ConsultaCPFVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/Email", new ConsultaEmailVHWeb());
        helpers.put("/Ecommerce/ValidarUsuario", new ValidaUsuarioVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/CEP", new ConsultaCEPVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizarCliente", new AtualizarClienteVHWeb());
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
