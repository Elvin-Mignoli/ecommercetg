/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

/**
 *
 * @author Felipe Monteiro
 */
//imports do programa
import java.text.ParseException;

public class Cliente extends Usuario
{

    public Cliente(String nome, String sobrenome, String cpf, Endereco endereco, Contato contato, String sexo, String dataNascimento, String tipoConta, String email, String senha) throws ParseException
    {
        super(nome, sobrenome, cpf, endereco, contato, sexo, dataNascimento, tipoConta, email, senha);
    }

    public Cliente()
    {
    }//default
}
