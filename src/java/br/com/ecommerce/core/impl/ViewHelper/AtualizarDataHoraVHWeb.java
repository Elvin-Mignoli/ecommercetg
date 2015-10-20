/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.IStrategy.AtualizarDataHoraIStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.util.json.JSONObject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author java
 */
public class AtualizarDataHoraVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Integer id = new Integer(request.getParameter("txtId"));
        String dataInicio = request.getParameter("txtDataInicio");
        String dataFim = request.getParameter("txtDataTermino");
        String hora = request.getParameter("txtHora");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Pedido p = new Pedido();

        try
        {
            p.setId(id);
            p.setDataInicio(format.parse(dataInicio));
            p.setDataFim(format.parse(dataFim));
            format = new SimpleDateFormat("HH:mm");
            p.setHoraConsultoria(Calendar.getInstance());
            p.getHoraConsultoria().setTimeInMillis(format.parse(hora).getTime());
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        IStrategy strategy = new AtualizarDataHoraIStrategy();

        Resultado rs = strategy.processar(p);

        if (rs.getEntidade() == null)
        {
            response.getWriter().write("");
        } 
        else
        {
            JSONObject js = new JSONObject();
            format = new SimpleDateFormat("dd/MM/yyyy");
            js.put("dataInicio", format.format(p.getDataInicio()));
            js.put("dataFim", format.format(p.getDataFim()));
            js.put("hora", hora);

            System.out.println(js.toString());

            response.getWriter().write(js.toString());
        }
    }

}
