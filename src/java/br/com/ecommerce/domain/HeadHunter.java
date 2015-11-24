/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.ArrayList;

/**
 *
 * @author henrique
 */
public class HeadHunter extends Usuario{
    
    private String cnpj;
    private String nome_empresa;
    private ArrayList<EntidadeDominio> prestadores = new ArrayList<>();

    public HeadHunter(String cnpj, String nome_empresa, String tipoConta, int status, String senha, String nome, String sobrenome, String dataNascimento, Contato contato, Endereco endereco, String sexo, String cpf, String email) {
        super(tipoConta, status, senha, nome, sobrenome, dataNascimento, contato, endereco, sexo, cpf, email);
        this.cnpj = cnpj;
        this.nome_empresa = nome_empresa;
    }

    
    public HeadHunter(){}
    
    public HeadHunter(String cpf){
        super.setCpf(cpf);
    }

    public ArrayList<EntidadeDominio> getPrestadores() {
        return prestadores;
    }

    public void setPrestadores(ArrayList<EntidadeDominio> prestadores) {
        this.prestadores = prestadores;
    }
    
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }
    
    
}
