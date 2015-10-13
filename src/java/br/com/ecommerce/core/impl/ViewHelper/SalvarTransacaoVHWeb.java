/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Transacao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class SalvarTransacaoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        Transacao tr = new Transacao();
        
        tr.setPrestador(new PrestadorServico());
        tr.getPrestador().setId(new Integer(request.getParameter("txtIdPrestador")));
        tr.setCliente(new Cliente());
        tr.getCliente().setId(new Integer(request.getParameter("txtIdCliente")));
        //tr.set
        
        return tr;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
