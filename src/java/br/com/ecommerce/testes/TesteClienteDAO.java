/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.application.EntidadeAplicacao;
import br.com.ecommerce.core.impl.dao.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class TesteClienteDAO {
    public static void main(String[] args) throws SQLException, ParseException {
        Endereco end = new Endereco();
        end.setBairro("Colorado");
        end.setCep("08655120");
        end.setCidade("Suzano");
        end.setComplemento("Apt 2");
        end.setEstado("São Paulo");
        end.setLogradouro("Rua: 10");
        end.setNumero("100");
        
      
        Contato contato = new Contato("47471515","996147755");
        Cliente cliente = new Cliente("Elvin", "Mignoli","0000231221" , end, contato, "M", "1992/05/11", "Cliente", "Elvin-mig@hotmail.com","1234");
        ClienteDAO clienteDAO = new ClienteDAO();
        
        //salvar
        //clienteDAO.salvar(cliente);
        
        
        //excluir
       cliente.setId(15);
       end.setId(27);
       //clienteDAO.excluir(cliente);
       
       //consultar um cliente
        Cliente clienteConsult = new Cliente();
        /*clienteConsult.setId(16);
        clienteConsult = (Cliente) clienteDAO.consultarUm(clienteConsult);
        System.out.println("NOME: " + clienteConsult.getNome());
        System.out.println("SOBRENOME: " + clienteConsult.getSobrenome());
        System.out.println("CPF: " + clienteConsult.getCpf());
        System.out.println("SEXO: " + clienteConsult.getSexo());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("DATA NASCIMENTO: " + format.format(clienteConsult.getDataNascimento()));
        System.out.println("ENDEREÇO: " );
        System.out.println("RUA: " + clienteConsult.getEndereco().getLogradouro());
        System.out.println("CONTATO: " + clienteConsult.getContato().getTelefone());
        System.out.println("EMAIL: " + clienteConsult.getEmail());
        System.out.println("SENHA: " + clienteConsult.getSenha());
        System.out.println("STATUS: " + clienteConsult.getStatus());
        System.out.println("TIPO DE CONTA: " + clienteConsult.getTipoConta());
        System.out.println("ID: " + clienteConsult.getId());*/
       
       
       //consultar varios clientes
      List<EntidadeDominio> lista = clienteDAO.consultar(clienteConsult);
       for(EntidadeDominio cl: lista)
       {
           Cliente consulaCliente = (Cliente) cl;
            System.out.println("\nNOME: " + consulaCliente.getNome());
            System.out.println("SOBRENOME: " + consulaCliente.getSobrenome());
            System.out.println("CPF: " + consulaCliente.getCpf());
            System.out.println("SEXO: " + consulaCliente.getSexo());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("DATA NASCIMENTO: " + format.format(consulaCliente.getDataNascimento()));
            System.out.println("ENDEREÇO: " );
            System.out.println("RUA: " + consulaCliente.getEndereco().getLogradouro());
            System.out.println("CONTATO: " + consulaCliente.getContato().getTelefone());
            System.out.println("EMAIL: " + consulaCliente.getEmail());
            System.out.println("SENHA: " + consulaCliente.getSenha());
            System.out.println("STATUS: " + consulaCliente.getStatus());
            System.out.println("TIPO DE CONTA: " + consulaCliente.getTipoConta());
            System.out.println("ID: " + consulaCliente.getId());
    }
}
}
