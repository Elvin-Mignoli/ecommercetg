/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Monteiro
 */
public abstract class BuscaCEP
{

    /**
     * Método para buscar um CEP e retornar o Endereco por JSON Os dados devem
     * ser processados no cliente com JS
     *
     * @param cep cep do cliente
     * @return json contendo os dados do endereco
     * @throws MalformedURLException Caso a url não esteja correta!
     */
    public static String getEndereco(String cep) throws MalformedURLException, IOException
    {
        try
        {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream input = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String json = "{";
            while (reader.read() > 0)
            {
                json += reader.readLine();
            }

            return json;

        } catch (MalformedURLException ex)
        {
            ex.printStackTrace();
            throw new MalformedURLException("erro ao buscar CEP");
        }
    }
}
