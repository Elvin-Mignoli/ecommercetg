/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Felipe Monteiro
 */
public class TesteHora
{
    public static void main(String[] args) throws ParseException
    {
        Calendar time = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        
        time.setTime(format.parse("13:10:02"));
    }
}
