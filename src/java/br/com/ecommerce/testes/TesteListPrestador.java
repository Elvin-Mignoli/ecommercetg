/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class TesteListPrestador
{
    public static void main(String[] args)
    {
        Cliente c = new Cliente();
        
        List<EntidadeDominio> entidades = new ArrayList<>();
        
        //entidades.add(c);
        
        PrestadorServico pt = new PrestadorServico();
        
        pt.setId(16);
        
        entidades.add(pt);
        
        pt = new PrestadorServico();
        
        pt.setId(16);
        
        if(entidades.contains(pt))
            System.out.println("Sim!");
        else
            System.out.println("Não");
    }
}
