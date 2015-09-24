/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class FiltrarConsultoriaPrestador implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
       Resultado resultado = new Resultado();
       Pedido pedido = (Pedido)entidade;
       List<EntidadeDominio> listaPedidos = new ArrayList<>();//Lista pra armazenar somentes pedidos do prestador logado
       List<EntidadeDominio> listaInteressados ;//Lista para armazenar somente a instancia do prestador logado
       for(EntidadeDominio en: pedido.getPedidos())
       {
            Pedido p = (Pedido) en;
            listaInteressados = new ArrayList<>();
            for(EntidadeDominio  pr :p.getPrestadores())
            {
                PrestadorServico prestador = (PrestadorServico) pr;
                if(prestador.getId()== pedido.getPrestadorFinalista().getId())//o prestador logado está na listaPedidos de interessado nesse pedido
                {//sim
                    listaInteressados.add(pr);
                    p.setPrestadores(listaInteressados);
                    listaPedidos.add(p);
                    
                }
            }
       }
       if(listaPedidos.isEmpty()) // a listaPedidos não encontrou nenhum pedido do prestador logado?     
       {//não
         listaPedidos = null;
       }
       resultado.setEntidades(listaPedidos);
       return resultado;
    }
    
}
