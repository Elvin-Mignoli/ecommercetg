/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class PrestadorServico extends Usuario
{
    private ArrayList<Competencia> habilidades;
    private String cnpj;
    private Status candidatura;
    private double valorConsultoria;
    private List<Avaliacao> avaliacoes;
    private double ranking;
    
    public PrestadorServico()
    {
        avaliacoes = new ArrayList<>();
    }//default
    
    public void addAvaliacao(Avaliacao avaliacao)
    {
        avaliacoes.add(avaliacao);
    }
    
    public List<Avaliacao> getAvaliacoes()
    {
        return avaliacoes;
    }
    
    public double getValorConsultoria() {
        return valorConsultoria;
    }

    public void setValorConsultoria(double valorConsultoria) {
        this.valorConsultoria = valorConsultoria;
    }
    
    
    public PrestadorServico(String nome, String sobrenome, String data, String cpf, Endereco end, Contato cont, String sexo, String tipoConta,
            String email, String senha, int status, ArrayList habilidades)
    {
        super(tipoConta, status, senha, nome, sobrenome, data, cont, end, sexo, cpf, email);
        this.habilidades = habilidades;
    }

    public PrestadorServico(String cpf)
    {
        super.setCpf(cpf);
    }

    public PrestadorServico(ArrayList<Competencia> habilidades, String cnpj, String nome, String sobrenome)
    {
        super();
        this.habilidades = habilidades;
        this.cnpj = cnpj;

    }

    public PrestadorServico(ArrayList<Competencia> habilidades, String cnpj, String nome, String sobrenome, Date dataNascimento, Contato contato, Endereco endereco, String idade, String sexo, String estadoCivil)
    {
        super();
        this.habilidades = habilidades;
        this.cnpj = cnpj;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public Status getCandidatura() {
        return candidatura;
    }

    public void setCandidatura(Status candidatura) {
        this.candidatura = candidatura;
    }
    
    /**
     * @return the habilidades
     */
    public ArrayList<Competencia> getHabilidades()
    {
        return habilidades;
    }

    /**
     * @param habilidades the habilidades to set
     */
    public void setHabilidades(ArrayList<Competencia> habilidades)
    {
        this.habilidades = habilidades;
    }
    
    
   @Override
    public boolean equals(Object o)
    {
        PrestadorServico entidade = null;
        
        if(!(o instanceof EntidadeDominio))
        {
            throw new ClassCastException("Requerido um objeto EntidadeDominio, ou subclasses");
        }
        else
        {
          entidade = (PrestadorServico) o;   
        }

        if (entidade == null)
        {
            throw new NullPointerException("EntidadeDominio não pode ser NULL");
        } 
        else 
            return entidade.getId().equals(this.getId()); //return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

    public double getRanking()
    {
        return ranking;
    }

    public void setRanking(double ranking)
    {
        this.ranking = ranking;
    }
}
