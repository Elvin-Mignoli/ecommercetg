/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//pacotes do programa!
package br.com.ecommerce.model.dominio;
/**
 *
 * @author java
 */
//imports do programa
import java.util.Calendar;

public class EntidadeDominio
{
    private Integer id;
    private Calendar data;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Calendar getData()
    {
        return data;
    }

    public void setData(Calendar data)
    {
        this.data = data;
    }
}
