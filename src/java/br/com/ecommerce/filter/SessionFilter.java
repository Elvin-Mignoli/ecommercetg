/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.filter;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
@WebFilter(filterName = "SessionFilter", urlPatterns =
{
    "/JSP/Cliente/ClienteDashboard.jsp", "/JSP/PrestadorServico/PrestadorDashboard.jsp"
})
public class SessionFilter implements Filter
{

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SessionFilter()
    {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Usuario usuario = (Usuario) req.getSession().getAttribute("user");

        if (usuario == null) //nao existe usuario logado?
        {
            if(usuario instanceof Cliente)
                resp.sendRedirect("../../index.jsp");
            else if(usuario instanceof PrestadorServico)
                resp.sendRedirect("../../index.jsp");  //Mudar quanto tiver a tela de login do prestador!
            else
                resp.sendRedirect("../../index.jsp");
        } else
        {
            chain.doFilter(request, response);  // deixa a requisicao passar !
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy()
    {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig)
    {

    }
}
