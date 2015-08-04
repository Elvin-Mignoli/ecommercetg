/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.Date;

/**
 *
 * @author java
 */
public abstract class Pessoa extends EntidadeDominio
{
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private Contato contato;
    private Endereco endereco;
    private String idade;
    private String sexo;
    private String estadoCivil;
    
    public Pessoa() { } // construtor default!

    public Pessoa(String nome, String sobrenome, Date dataNascimento, Contato contato, Endereco endereco, String idade, String sexo, String estadoCivil)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.contato = contato;
        this.endereco = endereco;
        this.idade = idade;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public Pessoa(String nome, String sobrenome)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
    
    
    
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getIdade()
    {
        return idade;
    }

    public void setIdade(String idade)
    {
        this.idade = idade;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    public String getEstadoCivil()
    {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil)
    {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public Contato getContato()
    {
        return contato;
    }

    public void setContato(Contato contato)
    {
        this.contato = contato;
    }
    
    
}
