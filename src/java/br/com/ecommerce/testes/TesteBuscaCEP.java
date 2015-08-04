/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.util.BuscaCEP;
import java.io.IOException;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteBuscaCEP
{
    public static void main(String[] args) throws IOException
    {
        String cep = "08710-680";
        
        System.out.println(BuscaCEP.getEndereco(cep));
    }
}
