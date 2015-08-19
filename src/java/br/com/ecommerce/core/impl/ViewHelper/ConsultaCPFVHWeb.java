/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ExisteCliente;
import br.com.ecommerce.core.impl.IStrategy.ExistePrestador;
import br.com.ecommerce.core.impl.IStrategy.ValidaCPF;
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
 * @author Felipe Monteiro 
 */
public class ConsultaCPFVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        IStrategy bussines = new ValidaCPF();

        String cpf = request.getParameter("cpf").replace(".", "").replace("-", "");

        Resultado rs = bussines.processar(new Usuario(cpf));

        //decidino qual CPF buscar Cliente ou Prestador
        if (request.getRequestURI().contains("Cliente"))
        {
            if (rs.getMensagens().isEmpty()) //NÃ£o existe um CPF cadastrado?
            {
                bussines = new ExisteCliente();

                rs = bussines.processar(new Cliente(cpf));

                if (!rs.getMensagens().isEmpty()) //O CPF jÃ¡ existe?
                {
                    out.print("Já existe um usuário com esse CPF!");
                }
            } else
            {
                out.print("CPF Inválido!");
            }
        } else if (request.getRequestURI().contains("Prestador"))
        {
            if (rs.getMensagens().isEmpty()) //NÃ£o existe um CPF cadastrado?
            {
                bussines = new ExistePrestador();

                rs = bussines.processar(new PrestadorServico(cpf));

                if (!rs.getMensagens().isEmpty()) //O CPF jÃ¡ existe?
                {
                    out.print("Já existe um usuário com esse CPF!");
                }
            } else
            {
                out.print("CPF Inválido!");
            }
        }
    }
}
