/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.application;

import br.com.ecommerce.domain.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 * Classe feita para administrar os resultados
 * de uma Insercao, Alteracao, Excluir e Consulta
 * feita por uma fachada!
 */
public class Resultado extends EntidadeAplicacao
{
    private String mensagemSimples; //para receber uma mensagem simples de erro!
    private List<String> Mensagens = new ArrayList<>(); //para receber uma lista com mensagens!
    private List<EntidadeDominio> entidades = new ArrayList<>(); //para retornar várias entidadesDominio como resposta ("Consultar")
    private EntidadeDominio entidade = new EntidadeDominio();   //para retornar uma única entidadeDominio como resposta ("Consultar Um")
    
    // Métodos de Acesso!
    
    /*
        Método utilizado para retornar várias mensagens
    */
    
    public void setEntidades(List<EntidadeDominio> entidades)
    {
        this.entidades = entidades;
    }
    
    public String getMensagemSimples()
    {
        return mensagemSimples;
    }

    public void setMensagemSimples(String mensagemSimples)
    {
        this.mensagemSimples = mensagemSimples;
    }

    public List<String> getMensagens()
    {
        return Mensagens;
    }

    public void addMensagens(String Mensagens)
    {
        this.Mensagens.add(Mensagens);
    }

    /**
     * Utilize esse método junto com Consultar
     * para retornar vários resultados!
     * @return ArrayList<EntidadeDominio>;
     */
    public List<EntidadeDominio> getEntidades()
    {
        return entidades;
    }

    public void addEntidades(EntidadeDominio entidades)
    {
        this.getEntidades().add(entidades);
    }

    public EntidadeDominio getEntidade()
    {
        return entidade;
    }

    public void setEntidade(EntidadeDominio entidade)
    {
        this.entidade = entidade;
    }
}
