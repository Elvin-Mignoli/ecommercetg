/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class TesteAfinidadeSkills {
    
    public static void main(String[] args) throws SQLException {
        //Pedido
        System.out.println("Pedido skills");
        Pedido pedido = new Pedido();
        pedido.setId(2);
        PedidoDAO dao = new PedidoDAO();
        
        pedido = (Pedido)dao.consultarUm(pedido);
        
        List<String> skillsPedido = new ArrayList<>() ;
        for(String skill: pedido.getHabilidadePrestador())
        {
            skillsPedido.add(skill.replace(",", " ").trim().toUpperCase());
        }
        for(String s:skillsPedido)
        {
              System.out.println(s);
        }
        //Prestador
        PrestadorServico prestador = new PrestadorServico();
        prestador.setId(10);
        PrestadorServicoDAO daoP = new PrestadorServicoDAO();
        
        prestador = (PrestadorServico) daoP.consultarUm(prestador);
        List<String> skillsPrestador = new ArrayList<>();
        
        //tratamento de String
        System.out.println("Prestador skills");
        for(Competencia comp: prestador.getHabilidades())
        {
            skillsPrestador.add(comp.getDescricao().replace(",", " ").trim().toUpperCase());
        }
        for(String s:skillsPrestador)
        {
              System.out.println(s);
        }
      
        
        double contador = 0;
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
        System.out.println(contador);
        
        System.out.println((contador /skillsPedido.size()) * 100);
        /*
        Calculo de afinidade ao pedido em porcentagem.
          (contador  * size da Array de skill (100%))  * 100
       
           -contador: soma da quantidade de skill do prestador iguais ao do pedido
           -size da Array de skill: tamanho da lista de skill do pedido
        
        */
        double calculo = (contador /skillsPedido.size()) * 100;
        String s = String.valueOf(calculo);
        s= String.format("%.2f", calculo);  
        System.out.println(s);
        
    }
}
