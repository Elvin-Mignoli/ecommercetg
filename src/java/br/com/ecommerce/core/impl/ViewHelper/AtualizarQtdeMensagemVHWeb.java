/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.AlterarStatusMsgIStrategy;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class AtualizarQtdeMensagemVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = new Integer(request.getParameter("idMensagem"));
        
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        
        CaixaEntrada cx = user.getEntrada();
        
        IStrategy strategy = new AlterarStatusMsgIStrategy();
        
        Mensagem m = new Mensagem();
        
        for (EntidadeDominio ed : cx.getMensagens())
        {
            if(id == ed.getId())
            {
                m = (Mensagem) ed;
                m.setFlgAberto(true);
            }
        }
        
        Resultado rs = strategy.processar(m);
        
        if(rs == null)
        {
            user.setEntrada(cx);
        }
        else
        {
            request.setAttribute("MsgAtualiza", m);
        }
        
        request.getSession().setAttribute("user", user);
    }
}
