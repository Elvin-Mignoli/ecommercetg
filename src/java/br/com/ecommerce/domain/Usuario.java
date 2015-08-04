/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Elvin
 */
public abstract class Usuario extends EntidadeDominio
{
    private String tipoConta; // se o usuário é do tipo cliente ou prestador de serviço
    private int status;  // 0 - inativo   e  1 - ativo
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private Contato contato;
    private Endereco endereco;
    private String idade;
    private String sexo;
    private String estadoCivil;

    public Usuario(String tipoConta, int status, String email, String senha, String nome, String sobrenome)
    {
        this.tipoConta = tipoConta;
        this.status = status;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
    
    public Usuario(String tipoConta, String email, String senha) throws ParseException
    {
        this.tipoConta = tipoConta;
        this.email = email;
        this.senha = senha;
        this.status = 1;
    }

    public Usuario(String tipoConta, String email, String senha, String nome, String sobrenome)
    {
        this.tipoConta = tipoConta;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
    
    public Usuario()
    {
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
    
    
    
    /**
     * @return the tipoConta
     */
    public String getTipoConta()
    {
        return tipoConta;
    }

    /**
     * @param tipoConta the tipoConta to set
     */
    public void setTipoConta(String tipoConta)
    {
        this.tipoConta = tipoConta;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha()
    {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    /**
     * @return the status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status)
    {
        this.status = status;
    }
}
