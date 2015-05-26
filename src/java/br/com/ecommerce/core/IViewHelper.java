/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 * Interface para auxilio da View com o Controller!
 */
public interface IViewHelper
{
    //para retornar uma entidade com os dados preenchidos da View!
    /**
     * Método para processar uma requisição Http
     * e retornar um objeto de EntidadeDomínio preenchido
     * @param request requisição vinda do servidor
     * @return Retornar uma EntidadeDomínio populada pela View
     */
    public EntidadeDominio getEntidade(HttpServletRequest request);
    
    /**
     *  Método para setar visão do usuário final nas Páginas JSP
     * @param resultado implementação Resultado para captura dos resultados!
     * @param request Requisição da página
     * @param response Response da página
     */
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
