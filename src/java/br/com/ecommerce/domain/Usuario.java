/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Elvin
 */
public abstract class Usuario extends EntidadeDominio
{

    private String nome;
    private String sobrenome;
    private String cpf;
    private Endereco endereco;
    private Contato contato;
    private String sexo;
    private Date dataNascimento;//arrumar
    private String tipoConta; // se o usuário é do tipo cliente ou prestador de serviço
    private int status;  // 0 - inativo   e  1 - ativo
    private String email;
    private String senha;

    public Usuario(String nome, String sobrenome, String cpf, Endereco endereco, Contato contato, String sexo,
            String dataNascimento, String tipoConta, String email, String senha) throws ParseException
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
        this.sexo = sexo;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//formatar para  americano
        Date date = format.parse(dataNascimento);//formatar String em date
        this.dataNascimento = date;
        this.tipoConta = tipoConta;
        this.email = email;
        this.senha = senha;
        this.status = 1;
    }

    public Usuario()
    {
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf()
    {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco()
    {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    /**
     * @return the contato
     */
    public Contato getContato()
    {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(Contato contato)
    {
        this.contato = contato;
    }

    /**
     * @return the sexo
     */
    public String getSexo()
    {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
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
     * @return the sobrenome
     */
    public String getSobrenome()
    {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
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
