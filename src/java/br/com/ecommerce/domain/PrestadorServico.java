/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Elvin
 */
public class PrestadorServico extends Usuario{
    
    private ArrayList<Competencia> habilidades;

    
    public PrestadorServico(String nome, String sobrenome, String cpf, Endereco endereco, Contato contato, 
            String sexo, String dataNascimento, String tipoConta, String email, String senha,ArrayList lista) throws ParseException {
        super(nome, sobrenome, cpf, endereco, contato, sexo, dataNascimento, tipoConta, email, senha);
        this.habilidades = lista;
    }
    
    public PrestadorServico(){}//default

    /**
     * @return the habilidades
     */
    public ArrayList<Competencia> getHabilidades() {
        return habilidades;
    }

    /**
     * @param habilidades the habilidades to set
     */
    public void setHabilidades(ArrayList<Competencia> habilidades) {
        this.habilidades = habilidades;
    }
}
