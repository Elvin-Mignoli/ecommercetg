/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;
import br.com.ecommerce.domain.PrestadorServico;
import java.util.InputMismatchException;

/**
 *
 * @author Elvin
 */
public class ValidaCNPJ implements IStrategy{

    String documento;
    @Override
    public Resultado processar(EntidadeDominio entidade) {
        
        if(entidade instanceof PrestadorServico)
        {
            PrestadorServico usuario = (PrestadorServico)entidade;
             documento = usuario.getCnpj();
        }else 
        {
            HeadHunter usuario = (HeadHunter) entidade;
            documento = usuario.getCnpj();
        }
        
        Resultado resultado = new Resultado();
        String cnpj = documento.replace(".", "").replace("-", "").replace("/", "");
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais 
            if (cnpj.equals("00000000000000") || 
                   cnpj.equals("11111111111111") || 
                   cnpj.equals("22222222222222") || cnpj.equals("33333333333333") || 
                   cnpj.equals("44444444444444") || cnpj.equals("55555555555555") || 
                   cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || 
                   cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || 
                   (cnpj.length() != 14))
            {
                resultado.setMensagemSimples("CNPJ formados por uma sequência de números iguais");
                return resultado; 
            }
            
            char dig13, dig14; 
            int sm, i, r, num, peso;
            // "try" - protege o código para eventuais erros de conversao de tipo (int) 
            try { 
            // Calculo do 1o. Digito Verificador
                sm = 0; peso = 2; 
                for (i=11; i>=0; i--) 
                { // converte o i-ésimo caractere do CNPJ em um número: 
                // por exemplo, transforma o caractere '0' no inteiro 0 
                // (48 eh a posição de '0' na tabela ASCII) 
                    num = (int)(cnpj.charAt(i) - 48);
                    sm = sm + (num * peso); 
                    peso = peso + 1; 
                    if (peso == 10) 
                        peso = 2;
                    } 
                r = sm % 11;
                if ((r == 0) || (r == 1)) 
                    dig13 = '0'; 
                else 
                    dig13 = (char)((11-r) + 48);
                // Calculo do 2o. Digito Verificador 
                sm = 0; peso = 2; 
                for (i=12; i>=0; i--)
                { 
                    num = (int)(cnpj.charAt(i)- 48);
                    sm = sm + (num * peso);
                    peso = peso + 1; 
                    if (peso == 10) 
                        peso = 2; 
                } 
                r = sm % 11; 
                if ((r == 0) || (r == 1)) 
                    dig14 = '0';
                else 
                    dig14 = (char)((11-r) + 48); 
            // Verifica se os dígitos calculados conferem com os dígitos informados. 
                if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) 
                {
                     if(entidade instanceof PrestadorServico)
                    {
                        PrestadorServico prestador = (PrestadorServico) entidade;
                        prestador.setCnpj(cnpj);
                    }else 
                    {
                        HeadHunter head = (HeadHunter) entidade;
                        head.setCnpj(cnpj);
                    }
                    
                    return resultado; 
                }
                else {
                    resultado.setMensagemSimples("O CNPJ digitado não é válido");
                    return resultado;
                }
                     
            } catch (InputMismatchException erro)
            { 
                erro.printStackTrace();
                resultado.setMensagemSimples("Houve um erro inesperado");
                return resultado;
            }


    }
    
}
