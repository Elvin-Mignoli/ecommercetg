/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Monteiro
 */
public class BuscaPedidoMes extends EntidadeDominio
{
    private Integer meses[] = new Integer[4];
    private String status;
    private Map<String,Integer> values = new HashMap<>();

    public BuscaPedidoMes()
    {
        String mes[] = {"Janeiro","Fevereiro","Maio","Abril","Março","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        
        for (int i = 0; i < 11; i++)    //inicializando vetor
        {
            values.put(mes[i], 0);
        }
    }
    
    public Map<String, Integer> getValues()
    {
        return values;
    }

    public void setValues(Map<String, Integer> values)
    {
        this.values = values;
    }
    
    public Integer[] getMeses()
    {
        return meses;
    }

    public void setMeses(Integer[] meses)
    {
        this.meses = meses;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
