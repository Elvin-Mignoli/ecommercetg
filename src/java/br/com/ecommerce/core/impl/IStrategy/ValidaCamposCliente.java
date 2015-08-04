/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;

/**
 *
 * @author Felipe Monteiro
 */
public class ValidaCamposCliente implements IStrategy
{

    @Override
    public Resultado processar(EntidadeDominio entidade)
    {
        Cliente cliente = (Cliente) entidade;
        StringBuilder msg = new StringBuilder();
        Resultado resultado = new Resultado();
        
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
        
        Usuario usuario = (Usuario) entidade;
        
        if(usuario.getEmail() == null || usuario.getEmail().equals(""))
            msg.append("Email\n");
        
        if(usuario.getSenha() == null || usuario.getEmail().equals(""))
            msg.append("Senha\n");
        
        if(msg.length() > 0)
            msg.insert(0, "Campos Obrigatórios: \n");
        
        resultado.addMensagens(msg.toString());
        
        return resultado;
    }
}
