/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.domain.Status;

/**
 *
 * @author java
 */
public class TesteEnum
{
    public static void main(String[] args)
    {
        Status st = Status.ABERTO;
        
        System.out.println(st.getValue());
    }
}
