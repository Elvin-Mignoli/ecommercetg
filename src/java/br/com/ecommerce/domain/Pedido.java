/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class Pedido extends EntidadeDominio
{
    private List<String> habilidadePrestador = new ArrayList<>();
    private List<String> habilidadeCliente = new ArrayList<>(); 
    private Status status;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private PrestadorServico prestadorFinalista;
    private List<EntidadeDominio> prestadores;
    private Cliente cliente;
    private Double valor;
    private Integer qtdeInteressados;
    private Calendar horaConsultoria;
    private List<EntidadeDominio> pedidos;
    public static final String CLIENTE = "CLIENTE";
    public static final String PRESTADOR = "PRESTADOR";
    public static final String MURAL = "MURAL";
    private String consulta;
    public String getConsulta()
    {
        return consulta;
    }

    public void setConsulta(String consulta)
    {
        this.consulta = consulta;
    }
    
    public List<EntidadeDominio> getPedidos()
    {
        return pedidos;
    }

    public void setPedidos(List<EntidadeDominio> pedidos)
    {
        this.pedidos = pedidos;
    }
    
    public List<String> getHabilidadePrestador()
    {
        return habilidadePrestador;
    }

    public void setHabilidadePrestador(List<String> habilidadePrestador)
    {
        this.habilidadePrestador = habilidadePrestador;
    }

    public List<String> getHabilidadeCliente()
    {
        return habilidadeCliente;
    }
    
    public void setHabilidadeCliente(List<String> habilidadeCliente)
    {
        this.habilidadeCliente = habilidadeCliente;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public PrestadorServico getPrestadorFinalista()
    {
        return prestadorFinalista;
    }

    public void setPrestadorFinalista(PrestadorServico prestadorFinalista)
    {
        this.prestadorFinalista = prestadorFinalista;
    }

    public List<EntidadeDominio> getPrestadores()
    {
        return prestadores;
    }

    public void setPrestadores(List<EntidadeDominio> prestadores)
    {
        this.prestadores = prestadores;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Integer getQtdeInteressados()
    {
        return qtdeInteressados;
    }

    public void setQtdeInteressados(Integer qtdeInteressados)
    {
        this.qtdeInteressados = qtdeInteressados;
    }

    public Calendar getHoraConsultoria()
    {
        return horaConsultoria;
    }

    public void setHoraConsultoria(Calendar horaConsultoria)
    {
        this.horaConsultoria = horaConsultoria;
    }
    
    public void addHabilidadeRequerida(String value)
    {
        if(!habilidadePrestador.contains(value))    //nao contem a habilidade na lista?
            habilidadePrestador.add(value);
    }
    
    public void addHabilidadeRequerida(String... values)
    {
        for (String value : values)
        {
            if (!habilidadePrestador.contains(value))
            {
                habilidadePrestador.add(value);
            }
        }
    }
    
    public void addHabilidadeCliente(String value)
    {
        if(!habilidadeCliente.contains(value))  //nao contem habilidade na lista?
            habilidadeCliente.add(value);
    }
    
    public void addHabilidadeCliente(String... values)
    {
        for(String v : values)
        {
            if(!habilidadeCliente.contains(v))
                habilidadeCliente.add(v);
        }
    }
    
}
