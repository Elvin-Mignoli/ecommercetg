/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.Date;

/**
 *
 * @author Felipe Monteiro
 */
//imports do programa

public class Cliente extends Usuario
{
    private String cpf;

    public Cliente(String cpf)
    {
        this.cpf = cpf;
    }
    
    public Cliente()
    {
    }//default

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }
    
    public Cliente(String cpf, String nome, String sobrenome)
    {
        this.cpf = cpf;
    }

    public Cliente(String cpf, String nome, String sobrenome, Date dataNascimento, Contato contato, Endereco endereco, String idade, String sexo, String estadoCivil)
    {
        this.cpf = cpf;
    }
}
