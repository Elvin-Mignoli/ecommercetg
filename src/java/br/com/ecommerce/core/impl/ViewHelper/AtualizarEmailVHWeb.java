/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarEmailVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("txtEmail");
        String novoEmail = request.getParameter("txtNovoEmail");
        String senha = request.getParameter("txtSenha");
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        
        if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha))
        {
            AutenticarDAO dao = new AutenticarDAO();
            
            usuario.setEmail(novoEmail);
            try
            {
                dao.atualizaEmail(usuario);
                request.setAttribute("MsgAtualiza", "Email atualizado com sucesso!");
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                request.setAttribute("MsgAtualiza", "Ocorreu um erro ao processar sua solitação, tente novamente mais tarde!");
            }
        }
        else
        {
            request.setAttribute("MsgAtualiza", "Email e Senha não correspondem!");
        }
        
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
    
}
