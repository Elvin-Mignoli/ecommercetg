/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
class ConsultarUmClienteVHWeb implements IViewHelper
{

    public ConsultarUmClienteVHWeb()
    {
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        //busca todos os dados do usuario
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        
    }
}
