/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.HeadHunterDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public class ExisteHeadHunter implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        HeadHunter head = (HeadHunter) entidade;
        Resultado resultado = new Resultado();
        
        HeadHunterDAO dao = new HeadHunterDAO();
        
        try
        {
            if(head.getCpf() != null)
            {
                if(dao.consultaHeadHunterCPF(entidade) == null)
                {
                    return resultado;
                }
                else
                {
                    resultado.addMensagens("Já existe um headHunter com esse CPF!");
                    return resultado;
                }
            }else
            {
                if(dao.consultaHeadHunterCNPJ(entidade) == null)
                {
                    return resultado;
                }
                else
                {
                    resultado.addMensagens("Já existe um cliente com esse CNPJ!");
                    return resultado;
                }  
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Algum erro inesperado ocorreu!");
            return resultado;
        }
    }
    
    
}
