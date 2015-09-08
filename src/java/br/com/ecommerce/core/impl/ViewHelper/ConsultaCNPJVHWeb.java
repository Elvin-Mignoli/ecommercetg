/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.ExistePrestador;
import br.com.ecommerce.core.impl.IStrategy.ValidaCNPJ;
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
public class ConsultaCNPJVHWeb implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
       return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        IStrategy bussines = new ValidaCNPJ();//strategy para validar um cnpj

        String cnpj = request.getParameter("cnpj").replace(".", "").replace("-", "").replace("/","");
        PrestadorServico prestador = new PrestadorServico();
        prestador.setCnpj(cnpj);
        
        Resultado rs = bussines.processar(prestador);//processar validação

        if (rs.getMensagemSimples()== null) //houve algum erro na validação?
        {//não
                bussines = new ExistePrestador();
                
                rs = bussines.processar(prestador);

                if (!rs.getMensagens().isEmpty()) //Já existe um prestador com esse cnpj?
                {
                    out.print("Já existe um usuário com esse CNPJ!");
                }
        } else
        {//sim
                out.print("CNPJ Inválido!");
        }
    }  
}
