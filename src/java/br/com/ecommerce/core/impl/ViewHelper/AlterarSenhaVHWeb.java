/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.AlterarEmail;
import br.com.ecommerce.core.impl.IStrategy.AlterarSenha;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elvin
 */
public class AlterarSenhaVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        String senha= request.getParameter("txtNovaSenha");
        
        Usuario usuario =(Usuario) request.getSession().getAttribute("user");
        usuario.setSenha(senha);
        IStrategy business = new AlterarSenha();
        Resultado rs = business.processar(usuario);
        
        if(rs.getMensagemSimples() == null)
        {
            request.setAttribute("MsgAtualiza", "Dados atualizados com sucesso!");
            if (request.getRequestURI().contains("Cliente"))// a requesição vem do cliente?
            {
               request.getSession().setAttribute("user",(Cliente) usuario);
            }else if(request.getRequestURI().contains("Prestador"))// a requesição vem do Prestador de servico?
            {
                 request.getSession().setAttribute("user",(PrestadorServico) usuario);
            }
        }else{
            request.setAttribute("MsgAtualiza", "Houve algum problema no servidor, tente novamente mais tarde!");   //retorna lista de mensagens
        }
        request.getRequestDispatcher("PrestadorDashboard.jsp").forward(request, response);
    }
    
    
}
