/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.HeadHunterDAO;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Elvin
 */
public class TesteHeadHunter {
    public static void main(String[] args) throws SQLException {
        
      /*Salvar
        Contato contado = new Contato("1147472010", "11985856232");
      Endereco end = new Endereco("Rua:2", "20", "08655120", "Colorado", "Suzano", "São Paulo", null);
      
      HeadHunter head = new HeadHunter();
      head.setContato(contado);
      head.setCpf("11111111111");
      head.setEndereco(end);
      head.setNome("Fulano");
      head.setSobrenome("Ciclano");
      head.setNome_empresa("HUE BR SA");
      head.setSexo("M");
      head.setTipoConta("HeadHunter");
      head.setStatus(1);
      head.setEmail("elvin@headhunter.com");
      head.setSenha("admin");
      HeadHunterDAO dao = new HeadHunterDAO();
      dao.salvar(head);*/
        
      /*Atualizar
        Contato contado = new Contato("1147472010", "11985856232");
      Endereco end = new Endereco("Rua:58", "222", "08655120", "Vila Amorim", "Suzano", "São Paulo", null);
      end.setId(68);
      HeadHunter head = new HeadHunter();
      head.setContato(contado);
      head.setCpf("22222222222");
      head.setEndereco(end);
      head.setNome("Elvin");
      head.setSobrenome("Bishop");
      head.setNome_empresa("HUE BR SA");
      head.setSexo("M");
      head.setTipoConta("HeadHunter");
      head.setStatus(1);
      head.setEmail("elvin@headhunter.com");
      head.setSenha("admin");
      head.setId(1);
      HeadHunterDAO dao = new HeadHunterDAO();
      dao.atualizar(head);*/
        
     /* Consultar
        List<EntidadeDominio> lista = new ArrayList<>();
      
      HeadHunterDAO dao = new HeadHunterDAO();
      
      lista =  dao.consultar(null);
      
      for(EntidadeDominio entidade : lista)
      {
          HeadHunter head = (HeadHunter) entidade;
          System.out.println("Nome: " + head.getNome());
      }*/
        
      HeadHunter head = new HeadHunter();
      head.setId(1);
      HeadHunterDAO dao = new HeadHunterDAO();
      head = (HeadHunter) dao.consultarUm(head);
      
        System.out.println("Nome: " + head.getNome());
    }
}
