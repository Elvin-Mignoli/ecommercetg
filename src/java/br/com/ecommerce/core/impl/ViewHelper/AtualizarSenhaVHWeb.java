/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarSenhaVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String senha = request.getParameter("txtSenha");
        String novaSenha = request.getParameter("txtNovaSenha");
        Usuario us = (Usuario) request.getSession().getAttribute("user");
        
        if(senha.equals(us.getSenha())) //senha é igual??
        {
            AutenticarDAO dao = new AutenticarDAO();
            us.setSenha(novaSenha);
            try
            {
                dao.atualizaSenha(us);
                request.setAttribute("MsgAtualiza", "Senha alterada com sucesso!");
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                request.setAttribute("MsgAtualiza", "Algum erro ocorreu, tente novamente mais tarde!");
            }
        }
        else
        {
            request.setAttribute("MsgAtualiza","Senha atual incorreta");
        }
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
}
