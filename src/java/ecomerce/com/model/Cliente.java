/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.com.model;

/**
 *
 * @author grimmjhow
 */
//imports do programa
import java.util.Calendar;

public class Cliente extends EntidadeDominio
{
    private String nome;
    private String cpf;
    private Endereco endereco;
    private Contato  contato;
    private String idade;
    private String sexo;
    private String estadoCivil;
    private Calendar dataNascimento;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public Contato getContato()
    {
        return contato;
    }

    public void setContato(Contato contato)
    {
        this.contato = contato;
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

    public Calendar getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
}
