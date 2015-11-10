/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class AfinidadePedido implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        
        PrestadorServico prestador = (PrestadorServico)entidade;
        Resultado resultado = new Resultado();
        //pedido
        for(EntidadeDominio p: prestador.getPedidos()){
            
            List<String> skillsPedido = new ArrayList<>() ;
            Pedido pedido = (Pedido) p;
            for(String skill: pedido.getHabilidadePrestador())
            {
                skillsPedido.add(skill.replace(",", " ").trim().toUpperCase());
            }


            //Prestador
             List<String> skillsPrestador = new ArrayList<>();

            //tratamento de String
            for(Competencia comp: prestador.getHabilidades())
            {
                skillsPrestador.add(comp.getDescricao().replace(",", " ").trim().toUpperCase());
            }

            //calculo
            double contador = 0;
            int size = pedido.getHabilidadePrestador().size();
            for(String pedidoSkill:skillsPedido)
            {
                for(String prestadorSkill:skillsPrestador)
                {
                    if(pedidoSkill.equals(prestadorSkill))
                    {
                        contador++;
                    }
                }
            }
          
           pedido.setAfinidadePercent(calcularAfinidade(contador, size));
           pedido.setAfinidade(identificarAfinidade(pedido.getAfinidadePercent()));
        }//for inicial
        
        resultado.setEntidade(prestador);
        return resultado;
    }
    
     public String calcularAfinidade(Double contador, int size)
     {
        String calculo = String.format("%.2f",(contador / size) * 100); 
          if(calculo.equals("0,00"))
              calculo = "0";
          return calculo;
     }
    public String identificarAfinidade(String calculo){
        
        double afinidade = Double.parseDouble(calculo.replace(",", "."));
        if(afinidade == 0 )
        {
            return "Nenhuma afinidade";
        }else if(afinidade > 0 &&afinidade <= 25)
        {
            return "Pouco afinidade";
        }else if(afinidade > 25 && afinidade <= 50)
        {
           return "Afinidade regular";
        } else if(afinidade > 50  && afinidade <= 75)
        {
            return "Boa afinidade";
        }else 
        {
            return "Ótima afinidade";
        }
        
    }
}
