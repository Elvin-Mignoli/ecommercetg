/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class TestePrestadorDAO {
    public static void main(String[]args) throws ParseException, SQLException
    {
        //salvar
        /*Endereco end = new Endereco("RUA:PEDRO SEGUNDO", "50", "08655152","COLORADO" , "SUZANO", "SP", "CASA DOS FUNDOS");
        Contato contato = new Contato("47474747", "996147855");
        ArrayList<Competencia> lista = new ArrayList<>();
        Competencia comp = new Competencia("Android");
        lista.add(comp);
        comp= new Competencia("COBIT");
        lista.add(comp);
        PrestadorServico prestador = new PrestadorServico("Marcos", "Silva", "36989645210", end, contato, "M", "1992/05/11",
                "Prestador", "marus.silva@gmail.com", "123", lista);
        PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
        prestDAO.salvar(prestador);*/
        
        
        //excluir
        /*PrestadorServico prest = new PrestadorServico();
        prest.setId(8);
        Endereco end = new Endereco();
        end.setId(37);
        prest.setEndereco(end);
        ArrayList<Competencia> lista =new ArrayList<>();
        Competencia comp = new Competencia();
        comp.setId(10);
        lista.add(comp);
        comp = new Competencia();
        comp.setId(11);
        lista.add(comp);
        prest.setHabilidades(lista);
        PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
        prestDAO.excluir(prest);*/
        
        
        //consultar um
       /*PrestadorServico prest = new PrestadorServico();
        prest.setId(7);
        Endereco end = new Endereco();
        end.setId(36);
        prest.setEndereco(end);
        PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
        prest = (PrestadorServico) prestDAO.consultarUm(prest);
        System.out.println("Nome: " + prest.getNome()+
                "\nEndereço: "+
                "\nRua: " + prest.getEndereco().getLogradouro());
        for(Competencia comp : prest.getHabilidades())
        {
            System.out.println("Competencias:" +
                    "\n"+ comp.getDescricao());
        }*/
        
        //consultar varios
        /*PrestadorServico prest = new PrestadorServico();
        PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
        List<EntidadeDominio> lista = prestDAO.consultar(prest);
        for(EntidadeDominio ps: lista)
        {
            prest = (PrestadorServico) ps;
            System.out.println("\nNOME: " +  prest.getNome());
            System.out.println("SOBRENOME: " + prest.getSobrenome());
            System.out.println("CPF: " + prest.getCpf());
            System.out.println("SEXO: " + prest.getSexo());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("DATA NASCIMENTO: " + format.format(prest.getDataNascimento()));
            System.out.println("ENDEREÇO: " );
            System.out.println("RUA: " + prest.getEndereco().getLogradouro());
            System.out.println("CONTATO: " + prest.getContato().getTelefone());
            System.out.println("EMAIL: " + prest.getEmail());
            System.out.println("SENHA: " + prest.getSenha());
            System.out.println("STATUS: " + prest.getStatus());
            System.out.println("TIPO DE CONTA: " + prest.getTipoConta());
            System.out.println("ID: " + prest.getId());
            System.out.println("COMPTETENCIA: ");
            for(Competencia comp: prest.getHabilidades())
            {
                System.out.println(comp.getDescricao() );
            }
            
        }*/
        
        //atualizar
        /* Endereco end = new Endereco();
            end.setBairro("Jardim do Bosque");
            end.setCep("08655525");
            end.setCidade("Suzano");
            end.setComplemento("Apt 10");
            end.setEstado("São Paulo");
            end.setLogradouro("Rua: 207");
            end.setNumero("522");
            end.setId(36);

            Contato contato = new Contato("47474410","996147740");
            
            ArrayList<EntidadeDominio> lista = new ArrayList<>();
            Competencia comp = new Competencia();
            comp.setDescricao("IOS");
            comp.setId(9);
            lista.add(comp);
            PrestadorServico prestador = new PrestadorServico("Marcus", "Mignoli","0000000000" , end, contato, "M", "1998/05/11", "Cliente",
                    "marcus@hotmail.com","1235",lista);
            prestador.setId(7);
            PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
            prestDAO.atualizar(prestador);*/
    }
}
