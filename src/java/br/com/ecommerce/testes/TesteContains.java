/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;

/**
 *
 * @author java
 */
public class TesteContains
{
    public static void main(String[] args)
    {
        Pedido pe = new Pedido();
        PrestadorServico pr = new PrestadorServico();
        
        pr.setId(10);
        
        pe.addPrestadores(pr);
        
        pr = new PrestadorServico();
        
        pr.setId(4);
       
        pe.addPrestadores(pr);
       
        pr = new PrestadorServico();
        
        pr.setId(10);
        
        if(pe.getPrestadores().contains(pr))
            System.out.println("Esse prestador já existe!");
        else
            System.out.println("Esse prestador não existe!");
        
    }
}
