/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;

/**
 *
 * @author Felipe Monteiro
 */
public class ValidaCamposCliente implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Cliente cliente = (Cliente) entidade;
        StringBuilder msg = new StringBuilder();
        
        if(cliente.getNome() == null || cliente.getNome().equals(""))
            msg.append("Nome\n");
        
        if(cliente.getSobrenome() == null || cliente.getNome().equals(""))
            msg.append("Sobre Nome\n");
        
        if(cliente.getCpf() == null || cliente.getCpf().equals(""))
            msg.append("CPF\n");
        
        if(cliente.getSexo() == null || cliente.getSexo().equals(""))
            msg.append("Sexo\n");
        
        if(cliente.getDataNascimento() == null)
            msg.append("Data de Nascimento\n");
        
        if(cliente.getEmail() == null || cliente.getEmail().equals(""))
            msg.append("Email\n");
        
        if(cliente.getSenha() == null || cliente.getEmail().equals(""))
            msg.append("Senha\n");
        
        if(msg.length() > 0)
            return "Campos Obrigatórios: \n"+msg.toString();
        
        return null;
    }
}
